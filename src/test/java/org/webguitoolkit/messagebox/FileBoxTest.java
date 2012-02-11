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

package org.webguitoolkit.messagebox;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.webguitoolkit.messagebox.file.FileChannel;

public class FileBoxTest extends AbstractMessageBoxTest {

	@Test
	public void fileTest() {

		MessageBox petersBox = new MessageBox();
		petersBox.putChannel(new FileChannel("file", "Peter", "/temp/messagebox"));
		IChannel fileChannel = petersBox.getChannel("file");
		fileChannel.connect();
		String body = "Hello again\nregards Peter";
		IMessage message = fileChannel.newMessage("Hi", body);
		assertNotNull(message);

		assertNotNull(message.getRecipients());
		message.getRecipients().add("paul");
		message.getRecipients().add("mary");

		message.setBody(body);
		message.setSubject("Hi");
		message.send();
		fileChannel.disconnect();

		MessageBox paulsBox = new MessageBox();
		paulsBox.putChannel(new FileChannel("file", "Paul", "/temp/messagebox"));
		fileChannel = paulsBox.getChannel("file");
		fileChannel.connect();
		List<IMessage> messages = fileChannel.receive(true);
		assertNotNull(messages);
		assertEquals(1, messages.size());
		assertEquals("Hi", messages.get(0).getSubject());
		assertEquals(body, messages.get(0).getBody());
		assertEquals("Peter", messages.get(0).getSender());
		// read again - must be empty
		messages = fileChannel.receive(true);
		assertTrue(messages.isEmpty());
	}

}
