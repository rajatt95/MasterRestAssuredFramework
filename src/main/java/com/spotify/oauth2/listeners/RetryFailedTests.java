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

package com.spotify.oauth2.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.utils.ConfigLoader;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = 1;

	@Override
	public boolean retry(ITestResult result) {

		boolean value = false;
		if (ConfigLoader.getInstance().getRetryFailedTests().equalsIgnoreCase(FrameworkConstants.getYes())) {
			if (count < retries) {
				count++;
				return true;
			}
		}
		return value;
	}
}
