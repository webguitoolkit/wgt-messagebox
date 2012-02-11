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
import java.util.HashSet;
import java.util.Set;

/**
 * An base implementation of the Message. The message can send itself by calling
 * the send() method.
 * 
 * @author peter@17sprints.de
 * 
 */
public abstract class AbstractMessage implements IMessage {

	private String subject;
	private String body;
	private Set<String> recipients = new HashSet<String>();;
	protected Set<IAttachment> attachments = new HashSet<IAttachment>();
	private Timestamp createdAt;
	private String sender;
	private IChannel channel;
	protected String contentType;

	protected AbstractMessage(String sender, IChannel channel, String subject, String body) {
		this.createdAt = new Timestamp(System.currentTimeMillis());
		this.sender = sender;
		this.channel = channel;
		this.subject = subject;
		this.body = body;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public Set<String> getRecipients() {
		return recipients;
	}

	@Override
	public Set<IAttachment> getAttachments() {
		return attachments;
	}

	@Override
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	@Override
	public void send() {
		getChannel().send(this);
	}

	@Override
	public abstract int maxSubjectSize();

	@Override
	public abstract int maxBodySize();

	@Override
	public String getSender() {
		return sender;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String toString() {
		return getClass().getSimpleName() + " { FROM= " + getSender() + " SUBJECT= " + getSubject() + " BODY="
				+ getBody() + " }";
	}

	public IChannel getChannel() {
		return channel;
	}
}
