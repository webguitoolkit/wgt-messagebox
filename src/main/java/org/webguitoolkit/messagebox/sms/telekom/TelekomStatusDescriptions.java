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
 * Übergreifende Status-Beschreibungen
 */
public interface TelekomStatusDescriptions {
	/**
	 * Deutsche Beschreibung des Statuscodes 0000:
	 *
	 * "Der Aufruf war erfolgreich."
	 */
	public static final String SUCCESS_DESCRIPTION_GERMAN = "Der Aufruf war erfolgreich.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0000:
	 *
	 * "The call was successful."
	 */
	public static final String SUCCESS_DESCRIPTION_ENGLISH = "The call was successful.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0001:
	 *
	 * "Ein interner Fehler ist aufgetreten."
	 */
	public static final String INTERNAL_ERROR_DESCRIPTION_GERMAN = "Ein interner Fehler ist aufgetreten.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0001:
	 *
	 * "An internal error occurred."
	 */
	public static final String INTERNAL_ERROR_DESCRIPTION_ENGLISH = "An internal error occurred.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0015:
	 *
	 * "Die Umgebung ist ungültig."
	 */
	public static final String ENVIRONMENT_INVALID_DESCRIPTION_GERMAN = "Die Umgebung ist ungültig.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0015:
	 *
	 * "The environment is invalid."
	 */
	public static final String ENVIRONMENT_INVALID_DESCRIPTION_ENGLISH = "The environment is invalid.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0020:
	 *
	 * "Es fehlt die Berechtigung, den Service zu nutzen."
	 */
	public static final String PERMISSIONS_MISSING_DESCRIPTION_GERMAN = "Es fehlt die Berechtigung, den Service zu nutzen.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0020:
	 *
	 * "No authorization to use the service."
	 */
	public static final String PERMISSIONS_MISSING_DESCRIPTION_ENGLISH = "No authorization to use the service.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0023:
	 *
	 * "Der HTTP-Accept Header ist unbekannt."
	 */
	public static final String ACCEPT_HEADER_UNKNOWN_DESCRIPTION_GERMAN = "Der HTTP-Accept Header ist unbekannt.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0023:
	 *
	 * "The HTTP Accept header is unknown."
	 */
	public static final String ACCEPT_HEADER_UNKNOWN_DESCRIPTION_ENGLISH = "The HTTP Accept header is unknown.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0030:
	 *
	 * "Unzureichende Quota-Punkte."
	 */
	public static final String QUOTAS_EXCEEDED_DESCRIPTION_GERMAN = "Unzureichende Quota-Punkte.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0030:
	 *
	 * "Insufficient quota points."
	 */
	public static final String QUOTAS_EXCEEDED_DESCRIPTION_ENGLISH = "Insufficient quota points.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0040:
	 *
	 * "Der Token ist abgelaufen."
	 */
	public static final String TOKEN_EXPIRED_DESCRIPTION_GERMAN = "Der Token ist abgelaufen.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0040:
	 *
	 * "Token has expired."
	 */
	public static final String TOKEN_EXPIRED_DESCRIPTION_ENGLISH = "Token has expired.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0042:
	 *
	 * "Das angegebene Konto existiert nicht oder gehört nicht dem angemeldeten Nutzer."
	 */
	public static final String ACCOUNT_MISMATCH_DESCRIPTION_GERMAN = "Das angegebene Konto existiert nicht oder gehört nicht dem angemeldeten Nutzer.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0042:
	 *
	 * "Account does not exist or does not belong to user."
	 */
	public static final String ACCOUNT_MISMATCH_DESCRIPTION_ENGLISH = "Account does not exist or does not belong to user.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0050:
	 *
	 * "Unzureichendes Guthaben."
	 */
	public static final String INSUFFICIENT_CREDITS_DESCRIPTION_GERMAN = "Unzureichendes Guthaben.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0050:
	 *
	 * "Insufficient credit."
	 */
	public static final String INSUFFICIENT_CREDITS_DESCRIPTION_ENGLISH = "Insufficient credit.";
	
	/**
	 * Deutsche Beschreibung des Statuscodes 0090:
	 *
	 * "Der übergebene Token war ungültig."
	 */
	public static final String TOKEN_INVALID_DESCRIPTION_GERMAN = "Der übergebene Token war ungültig.";
	
	/**
	 * Englische Beschreibung des Statuscodes 0090:
	 *
	 * "The transferred token was invalid."
	 */
	public static final String TOKEN_INVALID_DESCRIPTION_ENGLISH = "The transferred token was invalid.";
}