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
 * Status-Konstanten für den Telekom Service Send SMS
 */
public interface SendSmsStatusConstants extends TelekomStatusConstants {
	/**
	 * Die übergebene Mitteilung war zu lang.
	 */
	public static final int MESSAGE_TOO_LONG = 30002;
	
	/**
	 * Keine der übergebenen Rufnummern war gültig. (Siehe Rufnummernformate)
	 */
	public static final int INVALID_PHONE_NUMBER_FORMAT = 30003;
	
	/**
	 * Es wurden zu viele Empfänger angegeben
	 */
	public static final int TOO_MANY_NUMBERS = 30004;
	
	/**
	 * Der angegebene Sendername ist ungültig.
	 */
	public static final int SENDER_NAME_INVALID = 30005;
	
	/**
	 * Die übergebene Mitteilung enthält keine Zeichen.
	 */
	public static final int MESSAGE_INVALID = 30006;
	
	/**
	 * Es wurden keine Empfängerrufnummern übergeben.
	 */
	public static final int RECIPIENTS_INVALID = 30007;
	
}
