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

import org.webguitoolkit.messagebox.AbstractMessage;

public class TelekomSmsMessage extends AbstractMessage {

	protected TelekomSmsMessage(String sender, TelekomSmsChannel channel, String subject, String body) {
		super(sender, channel, subject, body);
		contentType = "text/plain";
	}
	
	/**
	 * This method does not change the content type, because SMS is always "text/plain"
	 */
	@Override
	public void setContentType(String ct) {
		// intentionally left empty
	}

	/**
	 * return 160 (SMS has only a subject)
	 */
	@Override
	public int maxSubjectSize() {
		return 0;
	}

	/**
	 * @return 0
	 */
	@Override
	public int maxBodySize() {
		return 0;
	}

}
