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

package org.webguitoolkit.messagebox.sms;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.webguitoolkit.messagebox.IChannel;
import org.webguitoolkit.messagebox.IMessage;
import org.webguitoolkit.messagebox.sms.telekom.SendSmsClient;
import org.webguitoolkit.messagebox.sms.telekom.SendSmsResponse;
import org.webguitoolkit.messagebox.sms.telekom.SendSmsStatusConstants;

/**
 * Sending SMS via the Telekom SMS web service.<br>
 * The following properties are required:<br>
 * user : the Telekom Developer Garden user name<br>
 * password : the password<br>
 * environment : "mock", "sandbox" or "production"<br>
 * Optionally the HTTP proxy can be set here using the following keys:<br>
 * "https.proxyHost","https.proxyPort","http.proxyHost","http.proxyPort"
 * 
 * @author peter@17sprints.de
 * 
 */
public class TelekomSmsChannel implements IChannel {

	private SendSmsClient client;

	private Properties properties;

	private String name;

	/**
	 * Required properties (sms.telekom) : user, password, environment
	 * 
	 * @param properties
	 */
	public TelekomSmsChannel(String name, Properties properties) {
		this.properties = properties;
		this.name = name;
	}

	/**
	 * @return "sms.telekom"
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return <code>true</code> for CAN_SEND, <code>false</code> otherwise
	 */
	@Override
	public boolean checkAbility(int ability) {
		switch (ability) {
		case IChannel.CAN_DELETE:
			return false;
		case IChannel.CAN_READ:
			return false;
		case IChannel.CAN_SEND:
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public void connect() {
		if (properties.get("https.proxyHost") != null && properties.get("https.proxyPort") != null) {
			System.setProperty("https.proxyHost", (String) properties.get("https.proxyHost"));
			System.setProperty("https.proxyPort", (String) properties.get("https.proxyPort"));
		}

		if (properties.get("http.proxyHost") != null && properties.get("http.proxyPort") != null) {
			System.setProperty("http.proxyHost", (String) properties.get("http.proxyHost"));
			System.setProperty("http.proxyPort", (String) properties.get("http.proxyPort"));
		}

		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String environment = properties.getProperty("environment");
		client = new SendSmsClient(user, password, environment);
	}

	@Override
	public void disconnect() {
		client = null;
	}

	@Override
	public void send(IMessage message) {
		StringBuffer numbers = new StringBuffer();
		Set<String> recipients = message.getRecipients();
		for (String recipient : recipients) {
			numbers.append(recipient).append(",");
		}
		SendSmsResponse response = client.sendSms(numbers.toString(), message.getSubject(), message.getSender(), null,
				null);

		if (!response.getStatus().equals(SendSmsStatusConstants.SUCCESS))
			throw new RuntimeException("Failed to send SMS" + response.getStatus().getStatusDescriptionEnglish());

	}

	/**
	 * NOT YET IMPLEMENTED
	 */
	@Override
	public List<IMessage> receive(boolean clear) {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * NOT SUPPORTED
	 */
	@Override
	public boolean delete(IMessage message) {
		throw new RuntimeException("DELETE SMS IS NOT SUPPORTED");
	}

	@Override
	public IMessage newMessage(String subject, String body) {
		return new TelekomSmsMessage("FIS", this, subject, body);
	}

	/**
	 * Check the availability of the needed properties (user,password,environment)
	 */
	@Override
	public void validateProperties() {
		if (properties.getProperty("user") == null || properties.getProperty("password") == null
				|| properties.getProperty("environment") == null)
			throw new IllegalArgumentException("missing mandatory property");

	}

}
