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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


/**
 * Service-Klasse, um den Telekom Security Token Service
 * über HTTPSUrlConnection zu nutzen.
 */
public class STSService {
	/**
	 * Ruft einen Security Token unter Angabe von Benutzername und Passwort vom 
	 * Telekom Security Token Server ab
	 * @param username
	 * 		Der Benutzername
	 * @param password
	 * 		Das Passwort
	 * @return
	 * 		Ein Security-Token
	 */
	public static STSToken getToken(String username, String password) {
		STSToken token;
		
		try {
			HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://sts.idm.telekom.com/rest-v1/tokens/odg")).openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic " + Base64.encodeToString((username + ":" + password).getBytes(), false));
			connection.setDoInput(true);
			
			connection.connect();
			
			InputStream is;
			
			switch(connection.getResponseCode()) {
			case 200:
				is = connection.getInputStream();
				break;
			case 401:
				throw new RuntimeException(new GeneralSecurityException("Unauthorized"));
			default:
				is = connection.getErrorStream();
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			JSONObject response = new JSONObject(br.readLine());
			
			token = new STSToken(response.getString("token"), new Date(connection.getExpiration()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return token;
	}
}
