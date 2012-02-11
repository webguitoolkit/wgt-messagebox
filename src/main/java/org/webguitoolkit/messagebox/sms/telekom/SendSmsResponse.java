/*
 * This file is part of the Telekom Java SDK
 * Copyright 2010 Deutsche Telekom AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.webguitoolkit.messagebox.sms.telekom;



/**
 * Ergebnis der Operation sendSms(Versand einer SMS / Versand einer Flash-SMS)
 */
public class SendSmsResponse extends TelekomServiceResponse {

	
	/**
	 * Konstruiert das Datenobjekt mit den angegebenen Werten.
	 * @param status
	 *		Die Telekom-Statusinformationen	
	 */
	public SendSmsResponse(TelekomServiceStatusResponse status){
		super(status);
	}
}