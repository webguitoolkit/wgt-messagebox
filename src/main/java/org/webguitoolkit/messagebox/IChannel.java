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

/**
 * Abstraction for the communication channel (e.g. mail, sms, twitter, ...).
 * Implementing class is fully responsible for providing the functionality or
 * not.
 * 
 * @author peter@17sprints.de
 * 
 */
public interface IChannel {

	public static final int CAN_READ = 1;
	public static final int CAN_SEND = 2;
	public static final int CAN_DELETE = 3;
	public static final int REQUIRES_AUTHENTICATION = 4;

	/**
	 * @return the unique channel name
	 */
	public String getName();

	/**
	 * Check the ability of the Channel to provide one or the other function
	 * 
	 * @param ability
	 *            use one of the contstants defined here
	 * @return true if the passed ability (see constants) is provided
	 */
	public boolean checkAbility(int ability);

	/**
	 * Connect to channel
	 */
	public void connect();

	/**
	 * Disconnect from channel
	 */
	public void disconnect();

	/**
	 * Send message by channel
	 * 
	 * @param message
	 */
	public void send(IMessage message);

	/**
	 * Receive list of messages
	 * 
	 * @param clear
	 *            delete messages when read
	 * @return list (may be empty)
	 */
	public List<IMessage> receive(boolean clear);

	/**
	 * Delete ths message from the channel
	 * 
	 * @param message
	 * @return
	 */
	public boolean delete(IMessage message);

	/**
	 * Create a new Message for this channel
	 * 
	 * @return
	 */
	public IMessage newMessage(String subject, String body);

	/**
	 * Validate the properties and throw Exception if not valid.
	 */
	public void validateProperties();

}
