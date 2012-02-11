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


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Service-Klasse, um den Telekom Service Send SMS
 * über HTTPSUrlConnection zu nutzen.
 */
public class SendSmsService {
	/**
	 * Versand einer SMS / Versand einer Flash-SMS
	 * @param token
	 *		Der Token, der zur Authentifizierung verwendet werden soll.
	 * @param
	 *		environment Die Umgebung, in welcher der Service genutzt werden soll. Mögliche Werte sind:<ul>[<Para><code>production</code></Para>[<Para><code>sandbox</code></Para>[<Para><code>mock</code></Para>]]]</ul>
	 * @param
	 *		number Die Empfängerrufnummern, durch Kommas (",") getrennt. (Siehe unterstützte Rufnummernformate)
	 * @param
	 *		message Die an die Empfängerrufnummern zu sendende Mitteilung.
	 * @param
	 *		originator Der Absender, so wie er beim Empfänger angezeigt wird.Die Angabe einer Rufnummer als Absender ist nur dann erlaubt, wenn diese vorher validiert wurde. (Siehe: Send SMS - Rufnummernvalidierung)
	 * @param
	 *		flash Legt fest, ob die SMS als Flash-SMS gesendet werden soll:[true - SMS wird als Flash-SMS gesendet[anderer Wert - SMS wird als normale SMS gesendet]]
	 * @param
	 *		account Konto-ID des Unterkontos, über welches die Service-Nutzung abgerechnet werden soll. Wird der Parameter nicht angegeben, erfolgt die Abrechnung über das Hauptkonto.Siehe: Kontobasierte Servicenutzung
	 * @return
	 *		Das Ergebnis des Vorgangs "Versand einer SMS / Versand einer Flash-SMS"
	 */
	public static SendSmsResponse sendSms(STSToken token, String environment, String number, String message, String originator, String flash, String account) {
		if(environment == null)
			throw new IllegalArgumentException("environment");
		if(number == null)
			throw new IllegalArgumentException("number");
		if(message == null)
			throw new IllegalArgumentException("message");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		
		try {
			
				
			
			int paramNum = 0;
			
			
			if(number != null) {
				
				
				bos.write(("number=" + URLEncoder.encode(number, "UTF-8")).getBytes("UTF-8"));
				
				paramNum++;
			}

			
			
			if(message != null) {
				
				if(paramNum != 0)
					bos.write('&');
				
				
				bos.write(("message=" + URLEncoder.encode(message, "UTF-8")).getBytes("UTF-8"));
				
				paramNum++;
			}

			
			
			if(originator != null) {
				
				if(paramNum != 0)
					bos.write('&');
				
				
				bos.write(("originator=" + URLEncoder.encode(originator, "UTF-8")).getBytes("UTF-8"));
				
				paramNum++;
			}

			
			
			if(flash != null) {
				
				if(paramNum != 0)
					bos.write('&');
				
				
				bos.write(("flash=" + URLEncoder.encode(flash, "UTF-8")).getBytes("UTF-8"));
				
				paramNum++;
			}

			
			
			if(account != null) {
				
				if(paramNum != 0)
					bos.write('&');
				
				
				bos.write(("account=" + URLEncoder.encode(account, "UTF-8")).getBytes("UTF-8"));
				
				paramNum++;
			}

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		String requestUrl = "https://gateway.developer.telekom.com/p3gw-mod-odg-sms/rest";
		
		try {
			requestUrl += "/" + URLEncoder.encode(environment, "UTF-8") + "/sms";
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}		
		HttpsURLConnection connection;
		
		try {
			connection = (HttpsURLConnection)(new URL(requestUrl)).openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			throw new RuntimeException(e);
		}
		
		connection.setRequestProperty("Authorization", "TAuth realm=\"https://odg.t-online.de\",tauth_token=\"" + token.getToken() + "\"");
		connection.setRequestProperty("User-Agent", "Telekom Java SDK/3.1.11");
		connection.setRequestProperty("Accept", "application/json");
		connection.setDoInput(true);
		
		if(baos.size() > 0) {
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		}
		
		connection.setAllowUserInteraction(false);
		
		String responseString;
		try {
			bos.flush();
			connection.setDoOutput(true);
			baos.writeTo(connection.getOutputStream());
			
			InputStream is;
			
			if(connection.getResponseCode() != 200) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
			}
				
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			responseString = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		JSONObject jsonResponse;
		try {
			jsonResponse = new JSONObject(responseString);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		
		return SendSmsDataFactory.createSendSmsResponse(jsonResponse);
	}
}
