/*
Copyright 2011 Endress+Hauser Infoserve GmbH & Co. KG 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package org.webguitoolkit.messagebox.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.webguitoolkit.messagebox.IChannel;
import org.webguitoolkit.messagebox.IMessage;

/**
 * This a simple test communication channel that writes and reads messages from
 * the file system. The filenames have the pattern "FROM " <Sender> " - " [.#]
 * ".txt", e.g. "FROM Peter - Hi.3.txt"
 * 
 * This channel expects the properties:<br>
 * file.location : specifies the location on the filesystem where tho box is
 * located<br>
 * file.location.create : with "true" or "false" <br>
 * file.owner : the name (address) of the box owner<br>
 * 
 * @author peter@17sprints.de
 * 
 */
public class FileChannel implements IChannel {

	private static final String FILE_NAME_DELIMITER = " - ";
	private static final String MSG_FILE_PREFIX = "FROM ";
	private static final String MSG_FILE_EXTENSION = ".txt";

	private String name;
	private String owner;
	private String location;

	/**
	 * Required properties: location, owner, location.create
	 * 
	 * @param name
	 *            the channel identifier
	 * 
	 * @param properties
	 *            the controlling properties location, owner and location.create
	 */
	public FileChannel(String channelName, String owner, String location) {
		this.name = channelName;
		this.owner = owner;
		this.location = location;
	}

	public FileChannel(String name, Properties properties) {
		this.name = name;
		this.owner = (String) properties.get("owner");
		this.location = (String) properties.get("location");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean checkAbility(int ability) {
		switch (ability) {
		case IChannel.CAN_DELETE:
			return true;
		case IChannel.CAN_READ:
			return true;
		case IChannel.CAN_SEND:
			return true;
		}
		return false;
	}

	@Override
	public void connect() {
		File box = new File(location);
		if (!box.exists()) {
			box.mkdirs();
		}
	}

	@Override
	public void disconnect() {
		// nothing to do
	}

	@Override
	public void send(IMessage message) {
		File box;
		for (String address : message.getRecipients()) {
			String recipientsBoxPath = location + "/" + address;
			box = new File(recipientsBoxPath);
			if (!box.exists())
				box.mkdirs();
			String filename = recipientsBoxPath + "/FROM " + owner + FILE_NAME_DELIMITER + message.getSubject();
			File file = new File(filename + MSG_FILE_EXTENSION);
			int attempt = 0;
			while (file.exists()) {
				file = new File(filename + "." + (++attempt) + MSG_FILE_EXTENSION);
			}
			try {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(message.getBody().getBytes());
				fos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<IMessage> receive(boolean clear) {

		String recipientsBoxPath = location + "/" + owner;

		File box = new File(recipientsBoxPath);
		if (!box.exists() || !box.isDirectory())
			return Collections.emptyList();

		File[] fileList = box.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.startsWith(MSG_FILE_PREFIX) && filename.endsWith(MSG_FILE_EXTENSION);
			}
		});

		if (fileList.length == 0)
			return Collections.emptyList();

		List<IMessage> result = new ArrayList<IMessage>();
		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			String subject = file.getName();
			subject = subject.substring(MSG_FILE_PREFIX.length());
			int index = subject.indexOf(FILE_NAME_DELIMITER);
			String sender = subject.substring(0, index);
			subject = subject.substring(index + FILE_NAME_DELIMITER.length());
			index = subject.indexOf('.');
			subject = subject.substring(0, index);
			FileMessage message = new FileMessage(sender, this, subject, null);
			try {
				Scanner scanner = new Scanner(file);
				StringBuffer body = new StringBuffer();
				while (scanner.hasNextLine()) {
					body.append(scanner.nextLine());
					if (scanner.hasNextLine())
						body.append("\n");
				}
				scanner.close();
				message.setBody(body.toString());
				result.add(message);
				// delete file if desired
				if (clear)
					file.delete();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}

		}
		return result;
	}

	@Override
	public boolean delete(IMessage message) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public IMessage newMessage(String subject, String body) {
		return new FileMessage(owner, this, subject, body);
	}

	@Override
	public void validateProperties() {
		if (owner == null || location == null)
			throw new IllegalArgumentException("missing mandatory property");
	}

}
