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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.webguitoolkit.messagebox.file.FileChannel;
import org.webguitoolkit.messagebox.mail.MailChannel;
import org.webguitoolkit.messagebox.ram.RamChannel;
import org.webguitoolkit.messagebox.sms.TelekomSmsChannel;

/**
 * The MessageBox is intended to pass around the different channels in the using
 * application. You can add as many channels as you like. Note that the
 * identifier returned by IChannel.getName() is used to store a channel in the
 * MessageBox channel map.<br>
 * The getChannel method calls the validation method of the channel to make sure
 * that the required information is available. <br>
 * To control a channels function usually properties are passed to the channel
 * implementation. To get a specific set of properties into a channel you shall
 * prefix them with what is returned by the IChannel.getName() method.
 * 
 * @author peter@17sprints.de
 * 
 */
public class MessageBox {

	private Map<String, IChannel> channels = new HashMap<String, IChannel>();
	private String[] knownChannels = new String[] { "ram", "file", "sms", "mail" };

	public MessageBox() {
	}

	/**
	 * Initialize the MessageBox from a set of properties. It is assumed that
	 * the properties starting with a channel identifier.
	 * 
	 * @param properties
	 */
	public MessageBox(Properties properties) {

		Map<String, Map<String, Properties>> configurations = extractPropertyPackages(properties);
		Set<Entry<String, Map<String, Properties>>> entrySet = configurations.entrySet();
		for (Entry<String, Map<String, Properties>> entry : entrySet) {
			String channelType = entry.getKey();
			Map<String, Properties> channelConfigrations = entry.getValue();

			if ("ram".equals(channelType)) {
				Set<Entry<String, Properties>> ramChannels = channelConfigrations.entrySet();
				for (Entry<String, Properties> ramEntry : ramChannels) {
					String name = ramEntry.getKey();
					Properties props = ramEntry.getValue();
					putChannel(new RamChannel(name, props));
				}
			} else if ("sms".equals(channelType)) {
				Set<Entry<String, Properties>> smsChannels = channelConfigrations.entrySet();
				for (Entry<String, Properties> smsEntry : smsChannels) {
					String name = smsEntry.getKey();
					Properties props = smsEntry.getValue();
					putChannel(new TelekomSmsChannel(name, props));
				}
			} else if ("file".equals(channelType)) {
				Set<Entry<String, Properties>> fileChannels = channelConfigrations.entrySet();
				for (Entry<String, Properties> fileEntry : fileChannels) {
					String name = fileEntry.getKey();
					Properties props = fileEntry.getValue();
					putChannel(new FileChannel(name, props));
				}
			} else if ("mail".equals(channelType)) {
				Set<Entry<String, Properties>> mailChannels = channelConfigrations.entrySet();
				for (Entry<String, Properties> mailEntry : mailChannels) {
					String name = mailEntry.getKey();
					Properties props = mailEntry.getValue();
					putChannel(new MailChannel(name, props));
				}
			}
		}

	}

	public Map<String, Map<String, Properties>> extractPropertyPackages(Properties properties) {
		Map<String, Map<String, Properties>> result = new HashMap<String, Map<String, Properties>>();
		for (int i = 0; i < knownChannels.length; i++) {

			Properties channelProps = extractPropertiesStartingWith(knownChannels[i], properties);
			if (!channelProps.isEmpty()) {
				Map<String, Properties> props = extractPropertyMapsByFirstSeparator(channelProps);
				result.put(knownChannels[i], props);
			}
		}
		return result;
	}

	private Map<String, Properties> extractPropertyMapsByFirstSeparator(Properties channelProps) {
		Map<String, Properties> result = new HashMap<String, Properties>();
		Set<Entry<Object, Object>> entrySet = channelProps.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			int index = key.indexOf(".");
			String name = key.substring(0, index);
			String newKey = key.substring(index + 1);
			if (result.get(name) == null)
				result.put(name, new Properties());
			result.get(name).put(newKey, value);
		}
		return result;
	}

	private Properties extractPropertiesStartingWith(String locator, Properties properties) {
		if (locator == null)
			throw new IllegalStateException("source shall not be null");

		Properties result = new Properties();

		locator = locator + ".";

		for (Iterator<Object> it = properties.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			if (key.startsWith(locator)) {
				String value = (String) properties.get(key);
				key = key.substring(locator.length());
				result.put(key, value);

			}
		}

		return result;
	}

	/**
	 * @return the set of known channel keys.
	 */
	public Set<String> getAvailableChannels() {
		return channels.keySet();
	}

	/**
	 * Get the channel with name
	 * 
	 * @param name
	 * @return
	 */
	public IChannel getChannel(String name) {
		IChannel channel = channels.get(name);
		if (channel == null)
			return null;
		channel.validateProperties();
		return channel;

	}

	/**
	 * Put a channel into the MessageBox. Note that a channel overwrites a
	 * already registered channel with the same name.
	 * 
	 * @param channel
	 */
	public void putChannel(IChannel channel) {
		channels.put(channel.getName(), channel);
	}
}
