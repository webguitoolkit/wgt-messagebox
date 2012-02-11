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

import java.sql.Timestamp;
import java.util.Set;

/**
 * Abstraction of a Message. Depending on the Carrier subject, body, and
 * attachments are used. The message is created via a factory method of the
 * channel. Therefore the send() method knows how where to go.
 * 
 * @author peter@17sprints.de
 */
public interface IMessage {

	/**
	 * @return the author of this message
	 */
	public String getSender();

	/**
	 * @return the subject or <code>null</code>
	 */
	public String getSubject();

	/**
	 * Set the subject string. Will be truncated if longer than maxSubjectSize()
	 * 
	 * @param subject
	 *            the text
	 */
	public void setSubject(String subject);

	/**
	 * @return the body or <code>null</code>
	 */
	public String getBody();

	/**
	 * Set the body text. Will be truncated if longer than maxBodySize().
	 * 
	 * @param body
	 *            the Body string
	 */
	public void setBody(String body);

	/**
	 * The recipients for this message
	 * 
	 * @return a collection of recipient to add/read their addresses
	 */
	public Set<String> getRecipients();

	/**
	 * Get list of attachments.
	 * 
	 * @return the collection (may be empty) to add/get attachments. If
	 *         <code>null</code> is returned this channel is not able to handle
	 *         attachments.
	 */
	public Set<IAttachment> getAttachments();

	/**
	 * Send the message to the recipients via the connected channel
	 */
	public void send();

	/**
	 * @return the creation date of the message
	 */
	public Timestamp getCreatedAt();

	/**
	 * @return maximum length of the subject, 0 if no subject is allowed
	 */
	public int maxSubjectSize();

	/**
	 * @return maximum length of the body, 0 if no body is allowed
	 */
	public int maxBodySize();

	/**
	 * 
	 * @return the content type, by default "text/plain"
	 */
	public String getContentType();
	
	/**
	 * @param ct
	 */
	public void setContentType(String ct);
}
