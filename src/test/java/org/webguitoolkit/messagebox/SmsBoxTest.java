package org.webguitoolkit.messagebox;

import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.webguitoolkit.messagebox.sms.TelekomSmsChannel;

public class SmsBoxTest extends AbstractMessageBoxTest {

	Properties smsProperties = new Properties();

	@Before
	public void initialize() {
		smsProperties.setProperty("user", "<user>");
		smsProperties.setProperty("password", "<password>");
		smsProperties.setProperty("environment", "<environment>");
	}

	@Ignore
	public void sendSmsTest() throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		TelekomSmsChannel smsChannel = new TelekomSmsChannel("sms", smsProperties);
		messageBox.putChannel(smsChannel);
		smsChannel.connect();
		IMessage sms = smsChannel.newMessage("Hi this is a test", "A test");
		sms.getRecipients().add("<mobile no>");
		sms.send();
		smsChannel.disconnect();
	}

	@Test
	public void nopTest() {

	}
}
