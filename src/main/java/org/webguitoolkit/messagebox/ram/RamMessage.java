package org.webguitoolkit.messagebox.ram;

import org.webguitoolkit.messagebox.AbstractMessage;
import org.webguitoolkit.messagebox.IChannel;

public class RamMessage extends AbstractMessage {

	protected RamMessage(String sender, IChannel channel, String subject, String body) {
		super(sender, channel, subject, body);
	}

	@Override
	public int maxSubjectSize() {
		return 254;
	}

	@Override
	public int maxBodySize() {
		return 4096;
	}

}
