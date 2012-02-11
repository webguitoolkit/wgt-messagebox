package org.webguitoolkit.messagebox;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.webguitoolkit.messagebox.mail.MailChannel;

/**
 * Simple test for the mail channel
 * 
 * @author peter@17sprints.de
 * 
 */
public class MailBoxTest extends AbstractMessageBoxTest {

	Properties mailProperties = new Properties();

	@Before
	public void initialize() {
		mailProperties.setProperty("smtp.user", "17sprints <17sprints@googlemail.com>");
		mailProperties.setProperty("smtp.host", "smtp.googlemail.com");
		mailProperties.setProperty("smtp.port", "25");
		mailProperties.setProperty("smtp.login.user", "<email>");
		mailProperties.setProperty("smtp.login.password", "<password>");
		mailProperties.setProperty("pop3.login.user", "<email>");
		mailProperties.setProperty("pop3.login.password", "<password>");
		mailProperties.setProperty("pop3.host", "pop.googlemail.com");
		mailProperties.setProperty("pop3.port", "995");
	}

	@Ignore
	public void sendReceiveMailTest() throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		MailChannel mailChannel = new MailChannel("gmail", mailProperties);
		messageBox.putChannel(mailChannel);
		IMessage mail = mailChannel.newMessage("Hi", "I'm happy to be able to send you a mail. \nBest regards, Peter");
		mail.getRecipients().add("peter@17sprints.de");
		mail.getRecipients().add("17sprints@googlemail.com");
		mail.send();

		Thread.currentThread().sleep(10000);

		List<IMessage> inbox = mailChannel.receive(true);
		assertNotNull(inbox);
		assertFalse(inbox.isEmpty());

	}
	
	@Test public void nopTest() {
		
	}
}
