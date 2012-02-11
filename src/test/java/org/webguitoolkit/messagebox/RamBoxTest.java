package org.webguitoolkit.messagebox;

import java.util.List;

import org.junit.Test;
import org.webguitoolkit.messagebox.ram.RamChannel;

public class RamBoxTest extends AbstractMessageBoxTest {
	@Test
	public void sendReceiveRamTest() throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		IChannel ramChannel = new RamChannel("ram", "Peter");
		messageBox.putChannel(ramChannel);
		IMessage mail = ramChannel.newMessage("Hi", "I'm happy to be able to send you a mail. \nBest regards, Peter");
		mail.getRecipients().add("Paul");
		mail.getRecipients().add("Mary");
		mail.getRecipients().add("Peter");
		mail.send();
				
		List<IMessage> inbox = ramChannel.receive(true);
		assertNotNull(inbox);
		assertFalse(inbox.isEmpty());
	
		ramChannel = new RamChannel("ram", "Paul");
		messageBox.putChannel(ramChannel);
		inbox = ramChannel.receive(true);
		assertNotNull(inbox);
		assertFalse(inbox.isEmpty());
		
		ramChannel = new RamChannel("ram", "Mary");
		messageBox.putChannel(ramChannel);
		inbox = ramChannel.receive(true);
		assertNotNull(inbox);
		assertFalse(inbox.isEmpty());
	}
	
}
