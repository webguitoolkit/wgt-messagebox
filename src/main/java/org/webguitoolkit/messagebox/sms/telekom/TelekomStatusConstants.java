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
 * Status-Konstanten für alle Telekom Services
 */
public interface TelekomStatusConstants {
	/**
	 * Der Aufruf war erfolgreich.
	 */
	public static final int SUCCESS = 0;
	
	/**
	 * Ein interner Fehler ist aufgetreten.
	 */
	public static final int INTERNAL_ERROR = 1;
	
	/**
	 * Die Umgebung ist ungültig.
	 */
	public static final int ENVIRONMENT_INVALID = 15;
	
	/**
	 * Es fehlt die Berechtigung, den Service zu nutzen.
	 */
	public static final int PERMISSIONS_MISSING = 20;
	
	/**
	 * Der HTTP-Accept Header ist unbekannt.
	 */
	public static final int ACCEPT_HEADER_UNKNOWN = 23;
	
	/**
	 * Unzureichende Quota-Punkte.
	 */
	public static final int QUOTAS_EXCEEDED = 30;
	
	/**
	 * Der Token ist abgelaufen.
	 */
	public static final int TOKEN_EXPIRED = 40;
	
	/**
	 * Das angegebene Konto existiert nicht oder gehört nicht dem angemeldeten Nutzer.
	 */
	public static final int ACCOUNT_MISMATCH = 42;
	
	/**
	 * Unzureichendes Guthaben.
	 */
	public static final int INSUFFICIENT_CREDITS = 50;
	
	/**
	 * Der übergebene Token war ungültig.
	 */
	public static final int TOKEN_INVALID = 90;
	
	
	
	/**
	 * Status unbekannt
	 */
	public static final int UNKNOWN = -1;
}
