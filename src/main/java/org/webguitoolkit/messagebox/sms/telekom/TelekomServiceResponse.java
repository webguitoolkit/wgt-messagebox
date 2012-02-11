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
 * Antwortnachricht
 */
public class TelekomServiceResponse {
	/**
	 * Status-Informationen 
	 */
	private TelekomServiceStatusResponse status;

	/**
	 * Ruft die Status-Informationen ab
	 * @return
	 * 		Statusinformationen
	 */
	public TelekomServiceStatusResponse getStatus() {
		return status;
	}
	
	/**
	 * Erzeugt eine Antwortnachricht mit den angegebenen Status-Informationen.
	 * @param status
	 * 		Die Status-Informationen
	 */
	protected TelekomServiceResponse(TelekomServiceStatusResponse status) {
		this.status = status;
	}
}