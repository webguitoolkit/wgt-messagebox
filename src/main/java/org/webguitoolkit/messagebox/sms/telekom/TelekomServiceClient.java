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
 * Basisklasse eines Telekom Service Clients
 */
public abstract class TelekomServiceClient {
	/**
	 * Der Security-Token-Getter, welcher von diesem Client verwendet wird, um den Security-Token zu holen
	 */
	protected SecurityTokenGetter securityTokenGetter;
	
	/**
	 * Die Umgebung, in welcher der Client Serviceaufrufe durchführt
	 */
	protected String environment;
	
	/**
	 * Erzeugt einen Telekom Service Client unter Angabe eines Security-Token-Getters und der Umgebung.
	 * @param securityTokenGetter
	 * 		Der Security-Token-Getter, welcher von diesem Client verwendet wird, um den Security-Token zu holen
	 * @param environment
	 * 		Die Umgebung, in welcher der Client Serviceaufrufe durchführt
	 */
	public TelekomServiceClient(SecurityTokenGetter securityTokenGetter, String environment) {
		this.securityTokenGetter = securityTokenGetter;
		this.environment = environment;
	}
	
	/**
	 * Erzeugt einen Telekom API Client unter Angabe des Benutzernamens und Passworts.
	 * 
	 * Ein auf diesem Wege erzeugter Client nutzt den Standard Token-Getter, um den Security Token zu holen. 
	 * @param username
	 * 		Der Benutzername
	 * @param password
	 * 		Das Passwort
	 * @param environment
	 * 		Die Umgebung, in welcher der Client Serviceaufrufe durchführt
	 */
	public TelekomServiceClient(String username, String password, String environment) {
		this(new DefaultSecurityTokenGetter(username, password), environment);
	}
}