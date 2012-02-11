package org.webguitoolkit.messagebox.ram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.webguitoolkit.messagebox.IChannel;
import org.webguitoolkit.messagebox.IMessage;

/**
 * The RAM channel is intended for Unit testing with the MessageBox.
 * 
 * @author peter@17sprints.de
 * 
 */

public class RamChannel implements IChannel {

	private Map<String, List<IMessage>> mailBoxes = new HashMap<String, List<IMessage>>();
	private static Map<String, Map<String, List<IMessage>>> instances = new HashMap<String, Map<String, List<IMessage>>>();
	private String owner;
	private String name;

	public RamChannel(String name, String owner) {
		this.owner = owner;
		this.name = name;
		if (instances.get(name) == null)
			instances.put(name, new HashMap<String, List<IMessage>>());
		mailBoxes = instances.get(name);
	}

	public RamChannel(String name, Properties props) {
		this(name, props.getProperty("owner"));
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
		Set<String> recipients = message.getRecipients();
		for (String recipient : recipients) {
			List<IMessage> messages = mailBoxes.get(recipient);
			if (messages == null) {
				messages = new ArrayList<IMessage>();
				mailBoxes.put(recipient, messages);
			}
			messages.add(message);
		}
	}

	@Override
	public List<IMessage> receive(boolean clear) {
		List<IMessage> list = mailBoxes.get(owner);
		list = (list == null) ? (list = Collections.emptyList()) : (list);
		if (clear)
			mailBoxes.remove(owner);
		return list;
	}

	@Override
	public boolean delete(IMessage message) {
		List<IMessage> mailBox = mailBoxes.get(owner);
		if (!mailBox.isEmpty()) {
			mailBox.clear();
			return true;
		}
		return false;
	}

	@Override
	public IMessage newMessage(String subject, String body) {
		return new RamMessage(owner, this, subject, body);
	}

	@Override
	public void validateProperties() {
		if (owner == null)
			throw new IllegalArgumentException("missing mandatory property");
	}

}
