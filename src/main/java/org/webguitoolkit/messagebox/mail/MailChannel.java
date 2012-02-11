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

package org.webguitoolkit.messagebox.mail;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.io.IOUtils;
import org.webguitoolkit.messagebox.IChannel;
import org.webguitoolkit.messagebox.IMessage;

import com.sun.mail.pop3.POP3SSLStore;

/**
 * Implementation for mail messages using javax mail.
 * 
 * @author peter@17sprints.de
 * 
 */
public class MailChannel implements IChannel, Serializable {

	private static final long serialVersionUID = 571744066667434790L;
	private Properties properties;
	private String name;

	/**
	 * Required properties:<br>
	 * Sending via SMTP : "smtp.host", "smtp.port", "smtp.login.user", "smtp.login.password", "smtp.user"<br>
	 * Reading via POP3 : "pop3.login.user","pop3.login.password", "pop3.host", "mail.pop3.port"
	 * 
	 * @param properties
	 */

	public MailChannel(String name, Properties properties) {
		this.properties = properties;
		this.name = name;
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

	}

	@Override
	public void disconnect() {

	}

	@Override
	public void send(IMessage message) {
		try {
			Message mail = new MimeMessage(getSession());

			mail.addFrom(new InternetAddress[] { new InternetAddress(message.getSender()) });
			Set<String> recipients = message.getRecipients();
			if (recipients.isEmpty())
				return;
			for (String recipient : recipients) {
				mail.addRecipient(RecipientType.TO, new InternetAddress(recipient));
			}

			mail.setSubject(message.getSubject());
			mail.setContent(message.getBody(), message.getContentType());

			Transport.send(mail);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties sessionProps = new Properties();
		sessionProps.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		sessionProps.setProperty("mail.smtp.auth", "true");
		sessionProps.put("mail.smtp.starttls.enable", "true");
		sessionProps.setProperty("mail.smtp.host", properties.getProperty("smtp.host"));
		sessionProps.setProperty("mail.smtp.port", properties.getProperty("smtp.port"));

		return Session.getInstance(sessionProps, authenticator);
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = properties.getProperty("smtp.login.user");
			String password = properties.getProperty("smtp.login.password");
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}

	/**
	 * 
	 */
	@Override
	public List<IMessage> receive(boolean clear) {
		List<IMessage> result = new ArrayList<IMessage>();

		try {
			String user = properties.getProperty("pop3.login.user");
			String password = properties.getProperty("pop3.login.password");

			String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

			Properties pop3Props = new Properties();

			pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
			pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
			pop3Props.setProperty("mail.pop3.port", properties.getProperty("pop3.port"));
			pop3Props.setProperty("mail.pop3.socketFactory.port", properties.getProperty("pop3.port"));

			URLName url = new URLName("pop3", properties.getProperty("pop3.host"), Integer.valueOf(properties
					.getProperty("pop3.port")), "", user, password);

			Session session = Session.getInstance(pop3Props, null);
			Store store = new POP3SSLStore(session, url);
			store.connect();

			// Open the Folder
			Folder folder = store.getDefaultFolder();
			folder = folder.getFolder("INBOX");

			if (folder == null) {
				throw new RuntimeException("Invalid folder INBOX");
			}

			// try to open read/write and if that fails try read-only
			try {
				folder.open(Folder.READ_WRITE);
			} catch (MessagingException ex) {
				folder.open(Folder.READ_ONLY);
			}

			int count = folder.getMessageCount();
			// Message numbers start at 1
			for (int i = 1; i <= count; i++) {
				// Get a message by its sequence number
				Message m = folder.getMessage(i);
				Address[] from = m.getFrom();
				String type = from[0].getType();

				IMessage message = new MailMessage(from[0].toString(), this, m.getSubject(), getContent(m));

				result.add(message);

				// delete message ?
				if (clear)
					m.setFlag(Flags.Flag.DELETED, true);
			}

			// "true" actually deletes flagged messages from folder
			folder.close(clear);
			store.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public boolean delete(IMessage message) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public IMessage newMessage(String subject, String body) {
		return new MailMessage(properties.getProperty("smtp.user"), this, subject, body);
	}

	/**
	 * Drag the content from the message into a String
	 * 
	 * @param m
	 * @return
	 */
	private String getContent(Message m) {
		try {
			Object content = m.getContent();
			if (content instanceof String) {
				return (String) content;
			} else if (content instanceof Multipart) {

				Multipart multipart = (Multipart) content;
				int partCount = multipart.getCount();
				StringBuffer result = new StringBuffer();

				for (int j = 0; j < partCount; j++) {

					BodyPart bodyPart = multipart.getBodyPart(j);
					String mimeType = bodyPart.getContentType();

					Object partContent = bodyPart.getContent();
					if (partContent instanceof String) {
						result.append((String) partContent);
					} else if (partContent instanceof Multipart) {

						Multipart innerMultiPartContent = (Multipart) partContent;
						int innerPartCount = innerMultiPartContent.getCount();
						result.append("*** It has " + innerPartCount + "further BodyParts in it ***");
					} else if (partContent instanceof InputStream) {
						result.append(IOUtils.toString((InputStream) partContent));

					}
				} // End of for
				return result.toString();
			} else if (content instanceof InputStream)
				return IOUtils.toString((InputStream) content);

		} catch (Exception e) {
			return "ERROR reading mail content " + e.getMessage();
		}
		return "ERROR reading mail content - unknown format";
	}

	@Override
	public void validateProperties() {
		String[] keys = new String[] { "smtp.host", "smtp.port", "smtp.login.user", "smtp.login.password", "smtp.user",
				"pop3.login.user", "pop3.login.password", "pop3.host", "pop3.port" };

		for (int i = 0; i < keys.length; i++) {
			if (properties.getProperty(keys[i]) == null)
				throw new IllegalArgumentException("missing mandatory property " + keys[i]);
		}
	}
}
