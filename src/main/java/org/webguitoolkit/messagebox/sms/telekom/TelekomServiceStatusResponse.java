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
 * Statusinformationen
 */
public class TelekomServiceStatusResponse {
	/**
	 * Der Status-Code
	 */
	private String statusCode;
	
	/**
	 * Die Status-Konstante
	 */
	private int statusConstant;
	
	/**
	 * Die Status-Nachricht
	 */
	private String statusMessage;
	
	/**
	 * Die deutschsprachige Beschreibung des Status-Codes
	 */
	private String statusDescriptionGerman;
	
	/**
	 * Die englischsprachige Beschreibung des Status-Codes
	 */
	private String statusDescriptionEnglish;
	
	/**
	 * Ruft den Status-Code ab
	 * @return
	 * 		Der Status-Code
	 */
	public String getStatusCode() {
		return statusCode;
	}
	
	/**
	 * Ruft die Status-Nachricht ab
	 * @return
	 * 		Die Status-Nachricht
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
	
	/**
	 * Ruft die deutschsprachige Beschreibung des Status-Codes ab
	 * @return
	 * 		Die deutschsprachige Beschreibung des Status-Codes
	 */
	public String getStatusDescriptionGerman() {
		return statusDescriptionGerman;
	}
	
	/**
	 * Ruft die englischsprachige Beschreibung des Status-Codes ab
	 * @return
	 * 		Die englischsprachige Beschreibung des Status-Codes
	 */
	public String getStatusDescriptionEnglish() {
		return statusDescriptionEnglish;
	}
	
	/**
	 * Vergleicht einen Status mit einer vordefinierten Konstanten. Die
	 * Konstanten der Telekom Service Statuscodes sind in der jeweiligen
	 * Service Clientklasse definiert.
	 * @param o
	 * 	Die Status-Konstante, mit welcher dieser Status verglichen werden soll.
	 * @return
	 * 	Entspricht dieser Status einer vordefinierten Status-Konstante?
	 */
	public boolean equals(Object o) {
		if(o instanceof Integer) {
			int value = ((Integer)o).intValue();
			
			return equals(value);
		} else {
			return false;
		}
	}
	
	/**
	 * Vergleicht einen Status mit einer vordefinierten Konstanten. Die
	 * Konstanten der Telekom Service Statuscodes sind in der jeweiligen
	 * Service Clientklasse definiert.
	 * @param i
	 * 	Die Status-Konstante, mit welcher dieser Status verglichen werden soll.
	 * @return
	 * 	Entspricht dieser Status einer vordefinierten Status-Konstante?
	 */
	public boolean equals(int i) {
		return i == statusConstant;
	}
	
	/**
	 * Erzeugt die Telekom Service Status-Information mit den angegebenen Werten.
	 * @param statusCode
	 * 		Der Status-Code
	 * @param statusConstant
	 * 		Die Status-Konstante
	 * @param statusMessage
	 * 		Die Status-Nachricht
	 * @param statusDescriptionGerman
	 * 		Die deutschsprachige Beschreibung des Status-Codes
	 * @param statusDescriptionEnglish
	 * 		Die englischsprachige Beschreibung des Status-Codes
	 */
	public TelekomServiceStatusResponse(String statusCode, int statusConstant, String statusMessage, String statusDescriptionGerman, String statusDescriptionEnglish) {
		this.statusCode = statusCode;
		this.statusConstant = statusConstant;
		this.statusMessage = statusMessage;
		this.statusDescriptionGerman = statusDescriptionGerman;
		this.statusDescriptionEnglish = statusDescriptionEnglish;
	}
}