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

package com.spotify.oauth2.java_Mail_API;

import com.spotify.oauth2.constants.FrameworkConstants;

/**
 * Data for Sending EMail after execution
 */
public class EmailConfig {

	public static final String SERVER = "smtp.gmail.com";
	public static final String PORT = "587";

	public static final String FROM = "testtmail95@gmail.com";
	public static final String PASSWORD = "*********";

	/* "**********@gmail.com", */
	public static final String[] TO = {"testtmail95@gmail.com"};
	public static final String SUBJECT = FrameworkConstants.getProjectName();
}
