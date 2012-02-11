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
 * Service-Client für den Telekom Service Send SMS.
 */
public class SendSmsClient extends TelekomServiceClient
{
	/**
	 * Die interne Service-Nummer. Wird bei der Festlegung der Status-Konstanten
	 * verwendet
	 */
	public static final int SERVICE_NUMBER = 3;

	/**
	 * Erzeugt einen Send SMS Service-Client unter Angabe des zu
	 * verwendenden Token-Getters.
	 * @param securityTokenGetter
	 * 		Der Security-Token-Getter, welcher von diesem Client verwendet wird,
	 * 		um den Security-Token zu holen 
	 * @param environment
	 * 		Die zu nutzende Umgebung. Mögliche Werte sind:
	 * 		<ul>
	 * 			<li>production</li>
	 * 			<li>sandbox</li>
	 * 			<li>mock</li>
	 * 		</ul>
	 */
	public SendSmsClient(SecurityTokenGetter securityTokenGetter, String environment) {
		super(securityTokenGetter, environment);
	}

	/**
	 * Erzeugt einen Send SMS Service-Client unter Angabe des
	 * Benutzernamens und des Passworts.
	 * @param username
	 * 		Benutzername
	 * @param password
	 * 		Passwort 
	 * @param environment
	 * 		Die zu nutzende Umgebung. Mögliche Werte sind:
	 * 		<ul>
	 * 			<li>production</li>
	 * 			<li>sandbox</li>
	 * 			<li>mock</li>
	 * 		</ul>
	 */
	public SendSmsClient(String username, String password, String environment) {
		super(username, password, environment);
	}

	/**
	 * Versand einer SMS / Versand einer Flash-SMS
	 *
	 * @param number
	 *		Die Empfängerrufnummern, durch Kommas (",") getrennt. (Siehe unterstützte Rufnummernformate)
	 * @param message
	 *		Die an die Empfängerrufnummern zu sendende Mitteilung.
	 * @param originator
	 *		Optional: Der Absender, so wie er beim Empfänger angezeigt wird.Die Angabe einer Rufnummer als Absender ist nur dann erlaubt, wenn diese vorher validiert wurde. (Siehe: Send SMS - Rufnummernvalidierung)
	 * @param flash
	 *		Optional: Legt fest, ob die SMS als Flash-SMS gesendet werden soll:[true - SMS wird als Flash-SMS gesendet[anderer Wert - SMS wird als normale SMS gesendet]]
	 * @param account
	 *		Optional: Konto-ID des Unterkontos, über welches die Service-Nutzung abgerechnet werden soll. Wird der Parameter nicht angegeben, erfolgt die Abrechnung über das Hauptkonto.Siehe: Kontobasierte Servicenutzung
	 * @return Das Ergebnis der Operation Versand einer SMS / Versand einer Flash-SMS
	 */
	public SendSmsResponse sendSms(String number, String message, String originator, String flash, String account) {
		return SendSmsService.sendSms(securityTokenGetter.getToken(), environment, number, message, originator, flash, account);
	}
}
