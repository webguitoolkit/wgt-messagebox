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
 * Factory, welche Datenobjekte des Services Send SMS aus JSON-Antwortnachrichten erzeugt.
 */

public class SendSmsDataFactory {
	
	
	/**
	 * Erzeugt ein Datenobjekt des Typs SendSmsResponse aus der JSON-Antwortnachricht.
	 * @param o
	 * 	Die JSON-Antwortnachricht
	 * @return
	 * 	Ein Datenobjekt
	 */
	public static SendSmsResponse createSendSmsResponse(JSONObject o) {
		
		
		JSONObject responseStatusJSON = o.optJSONObject("status");
		
		String statusCode = null; 
		String statusMessage = null;
		
		if(responseStatusJSON != null) {
			statusCode = responseStatusJSON.optString("statusCode");
			statusMessage = responseStatusJSON.optString("statusMessage"); 
		}
		
		int statusConstant = getStatusConstant(statusCode);
		
		TelekomServiceStatusResponse responseStatus = new TelekomServiceStatusResponse(statusCode, statusConstant, statusMessage, getDescription(statusConstant, "german"), getDescription(statusConstant, "english"));
		
		
		return new SendSmsResponse(responseStatus);
	}

	
	
	
	/**
	 * Holt die Beschreibung einer Status-Konstante in einer bestimmten Sprache
	 * @param statusConstant
	 *  Die Status-Konstante
	 * @param language
	 *  Die Sprache
	 * @return
	 *	Die Beschreibung der Status-Konstante in der jeweiligen Sprache.
	 */
	public static String getDescription(int statusConstant, String language) {
		if(statusConstant == TelekomStatusConstants.SUCCESS ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.SUCCESS_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.SUCCESS_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.INTERNAL_ERROR ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.INTERNAL_ERROR_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.INTERNAL_ERROR_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.ENVIRONMENT_INVALID ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.ENVIRONMENT_INVALID_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.ENVIRONMENT_INVALID_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.PERMISSIONS_MISSING ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.PERMISSIONS_MISSING_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.PERMISSIONS_MISSING_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.ACCEPT_HEADER_UNKNOWN ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.ACCEPT_HEADER_UNKNOWN_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.ACCEPT_HEADER_UNKNOWN_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.QUOTAS_EXCEEDED ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.QUOTAS_EXCEEDED_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.QUOTAS_EXCEEDED_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.TOKEN_EXPIRED ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.TOKEN_EXPIRED_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.TOKEN_EXPIRED_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.ACCOUNT_MISMATCH ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.ACCOUNT_MISMATCH_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.ACCOUNT_MISMATCH_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.INSUFFICIENT_CREDITS ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.INSUFFICIENT_CREDITS_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.INSUFFICIENT_CREDITS_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == TelekomStatusConstants.TOKEN_INVALID ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.TOKEN_INVALID_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.TOKEN_INVALID_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == SendSmsStatusConstants.MESSAGE_TOO_LONG ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.MESSAGE_TOO_LONG_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.MESSAGE_TOO_LONG_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == SendSmsStatusConstants.INVALID_PHONE_NUMBER_FORMAT ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.INVALID_PHONE_NUMBER_FORMAT_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.INVALID_PHONE_NUMBER_FORMAT_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == SendSmsStatusConstants.TOO_MANY_NUMBERS ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.TOO_MANY_NUMBERS_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.TOO_MANY_NUMBERS_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == SendSmsStatusConstants.SENDER_NAME_INVALID ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.SENDER_NAME_INVALID_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.SENDER_NAME_INVALID_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == SendSmsStatusConstants.MESSAGE_INVALID ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.MESSAGE_INVALID_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.MESSAGE_INVALID_DESCRIPTION_ENGLISH;
			}
		}
		
		if(statusConstant == SendSmsStatusConstants.RECIPIENTS_INVALID ) {
			if(language.equals("german")) {
				return SendSmsStatusDescriptions.RECIPIENTS_INVALID_DESCRIPTION_GERMAN;
			} else {
				return SendSmsStatusDescriptions.RECIPIENTS_INVALID_DESCRIPTION_ENGLISH;
			}
		}
		
		return null;
	}
	
	/**
	 * Ermittelt die Status-Konstante für den angegebenen Status-Code
	 * @param statusCode
	 *  Der Status-Code für den die Status-Konstante ermittelt werden soll.
	 * @return
	 *  Die Status-Konstante für den angegebenen Status-Code
	 */
	public static int getStatusConstant(String statusCode) {
		if(statusCode == null) {
			return -1;
		} else {
			int statusConstant = Integer.parseInt(statusCode);
			
			
			if(statusConstant == TelekomStatusConstants.SUCCESS) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.INTERNAL_ERROR) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.ENVIRONMENT_INVALID) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.PERMISSIONS_MISSING) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.ACCEPT_HEADER_UNKNOWN) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.QUOTAS_EXCEEDED) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.TOKEN_EXPIRED) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.ACCOUNT_MISMATCH) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.INSUFFICIENT_CREDITS) {
				return statusConstant;
			}
			
			if(statusConstant == TelekomStatusConstants.TOKEN_INVALID) {
				return statusConstant;
			}
			
			return SendSmsClient.SERVICE_NUMBER * 10000 + statusConstant;
		}
	}
}
