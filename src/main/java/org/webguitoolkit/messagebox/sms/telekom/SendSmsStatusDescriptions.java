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
 * Status-Beschreibungen für den Telekom Service Send SMS
 */
public interface SendSmsStatusDescriptions extends TelekomStatusDescriptions {
	/**
	 * Deutsche Beschreibung des Statuscodes 0002 des Services sendSms:
	 *
	 * "Die übergebene Mitteilung war zu lang."
	 */
	public static final String MESSAGE_TOO_LONG_DESCRIPTION_GERMAN = "Die übergebene Mitteilung war zu lang.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0002 des Services sendSms:
	 *
	 * "The message sent was too long."
	 */
	public static final String MESSAGE_TOO_LONG_DESCRIPTION_ENGLISH = "The message sent was too long.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0003 des Services sendSms:
	 *
	 * "Keine der übergebenen Rufnummern war gültig. (Siehe Rufnummernformate)"
	 */
	public static final String INVALID_PHONE_NUMBER_FORMAT_DESCRIPTION_GERMAN = "Keine der übergebenen Rufnummern war gültig. (Siehe Rufnummernformate)";
	
	/**
	 * Englische Beschreibung des Statuscodes 0003 des Services sendSms:
	 *
	 * "None of the phone numbers were valid. (See Phone number formats)"
	 */
	public static final String INVALID_PHONE_NUMBER_FORMAT_DESCRIPTION_ENGLISH = "None of the phone numbers were valid. (See Phone number formats)";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0004 des Services sendSms:
	 *
	 * "Es wurden zu viele Empfänger angegeben"
	 */
	public static final String TOO_MANY_NUMBERS_DESCRIPTION_GERMAN = "Es wurden zu viele Empfänger angegeben";
	
	/**
	 * Englische Beschreibung des Statuscodes 0004 des Services sendSms:
	 *
	 * "Too many recipients were specified."
	 */
	public static final String TOO_MANY_NUMBERS_DESCRIPTION_ENGLISH = "Too many recipients were specified.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0005 des Services sendSms:
	 *
	 * "Der angegebene Sendername ist ungültig."
	 */
	public static final String SENDER_NAME_INVALID_DESCRIPTION_GERMAN = "Der angegebene Sendername ist ungültig.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0005 des Services sendSms:
	 *
	 * "The specified sender name is invalid."
	 */
	public static final String SENDER_NAME_INVALID_DESCRIPTION_ENGLISH = "The specified sender name is invalid.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0006 des Services sendSms:
	 *
	 * "Die übergebene Mitteilung enthält keine Zeichen."
	 */
	public static final String MESSAGE_INVALID_DESCRIPTION_GERMAN = "Die übergebene Mitteilung enthält keine Zeichen.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0006 des Services sendSms:
	 *
	 * "The transferred message does not contain any characters."
	 */
	public static final String MESSAGE_INVALID_DESCRIPTION_ENGLISH = "The transferred message does not contain any characters.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0007 des Services sendSms:
	 *
	 * "Es wurden keine Empfängerrufnummern übergeben."
	 */
	public static final String RECIPIENTS_INVALID_DESCRIPTION_GERMAN = "Es wurden keine Empfängerrufnummern übergeben.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0007 des Services sendSms:
	 *
	 * "No recipient number was transferred."
	 */
	public static final String RECIPIENTS_INVALID_DESCRIPTION_ENGLISH = "No recipient number was transferred.";
}