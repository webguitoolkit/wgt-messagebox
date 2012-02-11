package org.webguitoolkit.messagebox;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class MessageBoxTest extends AbstractMessageBoxTest {
	@Test
	public void propertyReaderTest() {
		Properties properties = new Properties();
		properties.put("file.myfile.path", "/temp/test1");
		properties.put("file.myfile.owner", "Peter");
		properties.put("file.yourfile.path", "/temp/test2");
		properties.put("file.yourfile.owner", "Paul");
		properties.put("ram.myram.owner", "Paul");

		MessageBox messageBox = new MessageBox();

		Map<String, Map<String, Properties>> propPacks = messageBox.extractPropertyPackages(properties);
		System.out.println(propPacks);
		assertEquals(2,propPacks.size());
		assertEquals(2,propPacks.get("file").size());
	}

	@Test
	public void instanziationTest() {
		Properties properties = new Properties();
		properties.put("file.myfile.location", "/temp/test1");
		properties.put("file.myfile.owner", "Peter");
		properties.put("file.yourfile.location", "/temp/test2");
		properties.put("file.yourfile.owner", "Paul");
		properties.put("ram.myram.owner", "Paul");

		MessageBox messageBox = new MessageBox(properties);

		assertEquals(3, messageBox.getAvailableChannels().size());
		System.out.println(messageBox.getAvailableChannels());
		IChannel myfileChannel = messageBox.getChannel("myfile");
		assertNotNull (myfileChannel);
		assertEquals("myfile",myfileChannel.getName());
		IMessage msg = myfileChannel.newMessage("sub", "body");
		msg.getRecipients().add("Frank");
		msg.send();
		
		
	}
}
