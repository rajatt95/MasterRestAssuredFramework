/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: REST Assured API Automation from scratch + Framework + CI (https://www.udemy.com/course/rest-assured-api-automation/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.RestResource.postAccount;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class TokenManager {

	private static String accessToken;
	private static Instant expiry_time;

	/**
	 * Get the Token
	 * 
	 * and, Re-new it only when it is expired
	 */
	/* synchronized -> Thread-safe*/
	public synchronized static String getToken() {

		try {
			if (accessToken == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Re-newing Token........");
				Response response = renewToken();
				accessToken = response.path("access_token");
				int expiryDurationInSeconds = response.path("expires_in");
				// expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds);
				/* Subtracting 5 minutes */
				/*
				 * Now, This method will take expiry_time as 3300 seconds instead of 3600
				 * seconds
				 */
				expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
			} else {
				System.out.println("Token is good to use..........");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ABORT!! Failed to get Token");
		}

		return accessToken;
	}

	// public static String renewToken() {
	private static Response renewToken() {
		HashMap<String, String> formParams = new HashMap<String, String>();

		formParams.put("client_id", ConfigLoader.getInstance().getClientID());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

		Response response = postAccount(formParams);

		if (response.getStatusCode() != 200) {
			throw new RuntimeException("ABORT!! Re-new token failed.");
		}
		// return response.path("access_token");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
		return response;
	}

}
