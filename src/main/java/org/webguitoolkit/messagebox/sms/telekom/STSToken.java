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
 * Ein Security Token
 */
public class STSToken {
	/**
	 * Die Token-Zeichenkette
	 */
	private String token;
	
	/**
	 * Ablaufzeitpunkt des Tokens.
	 * 
	 * Ein Token ist normalerweise 8 Stunden gültig.
	 */
	private Date validUntil;
	
	/**
	 * Ruft die Token-Zeichenkette ab
	 * @return
	 * 		Die Token-Zeichenkette
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * Ruft den Ablaufzeitpunkt des Tokens ab.
	 * @return
	 * 		Ablaufzeitpunkt des Tokens.
	 */
	public Date getValidUntil() {
		return validUntil;
	}
	
	/**
	 * Erzeugt ein Security Token mit den angegebenen Werten
	 * @param token
	 * 		Die Token-Zeichenkette
	 * @param validUntil
	 * 		Der Ablaufzeitpunkt des Tokens
	 */
	public STSToken(String token, Date validUntil) {
		this.token = token;
		this.validUntil = validUntil;
	}
}