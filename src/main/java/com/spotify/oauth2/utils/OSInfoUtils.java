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

package com.spotify.oauth2.utils;

public final class OSInfoUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private OSInfoUtils() {
	}

	public static String getOSInfo() {

		return System.getProperty("os.name").replace(" ", "_");

	}

}
