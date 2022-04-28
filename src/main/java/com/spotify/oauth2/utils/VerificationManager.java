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

import org.testng.Assert;

import com.spotify.oauth2.reports.ExtentLogger;

public class VerificationManager {

	public static void validateResponse(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			ExtentLogger.pass(
					message + "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail(
					message + "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validateResponse(boolean result, String message) {
		try {
			Assert.assertTrue(result);
			ExtentLogger.pass("<b><i>" + message + "</b></i>");
			// report.info("PASS : " + message);
		} catch (AssertionError assertionError) {
//			report.error("FAIL : " + message);
			ExtentLogger.fail("<b><i>" + message + "</b></i>");
			Assert.fail(message);
		}
	}

}
