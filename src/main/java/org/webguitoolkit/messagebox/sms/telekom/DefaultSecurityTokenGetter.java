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

import java.util.Date;

/**
 * Ein Security-Token-Getter, welcher Benutzername und Passwort speichert und
 * diese verwendet, um einen Security Token vom Security Token Server zu holen.
 * Bei Ablauf des Tokens wird der Token mit Hilfe der gespeicherten Authentifi-
 * zierungsinformationen erneut geholt.
 */
public class DefaultSecurityTokenGetter implements SecurityTokenGetter {
	private String username;
	private String password;
	private STSToken token;
	
	/**
	 * Konstruktor für den Standard Security-Token-Getter. Benutzername und
	 * Passwort werden gespeichert, um einen Token vom Security Token Server
	 * abzuholen.
	 * @param username
	 * 		Der Benutzername, welcher zur Authentifizierung
	 * 		gegenüber dem Security Token Server benutzt wird.	
	 * @param password
	 * 		Das Passwort, welches zur Authentifizierung
	 * 		gegenüber dem Security Token Server benutzt wird.
	 */
	public DefaultSecurityTokenGetter(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Falls noch kein Token abgefragt wurde, oder ein bereits abgefragter
	 * Token abgelaufen ist, wird ein Token vom Security Token Server geholt.
	 * Ansonsten wird der zwischengespeicherte Token einer vorhergehenden
	 * Abfrage zurückgegeben. 
	 * @return
	 * 	Security Token, welcher zur Authentifizierung genutzt werden soll.
	 */
	public STSToken getToken() {
		if(token == null || token.getValidUntil().before(new Date())) {
			token = STSService.getToken(username, password);
		}
		
		return token;
	}
}