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

package com.spotify.oauth2.constants;

import java.util.Date;

import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.OSInfoUtils;

public class FrameworkConstants {

	private static final String PROJECT_PATH = System.getProperty("user.dir");
	// private static final String RESOURCES_PATH = System.getProperty("user.dir") +
	// "/src/test/resources";

	public static final String ASSERTION_FOR_RESPONSE_STATUS_CODE = "Assertion for Response Status Code";
	public static final String ASSERTION_FOR_RESPONSE_HEADER = "Assertion for Response Headers";
	public static final String ASSERTION_FOR_RESPONSE_CUSTOM_FIELD = "Assertion for Response Custom Field";

	private static final String YES = "yes";
	private static final String NO = "no";

	private static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "/ExtentReports/";
	private static final String EXTENT_REPORT_NAME = "AutomationReport.html";
	private static String extentReportFilePath = "";

	/* ICONS - START */
	public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
	public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";

	public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
	public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
	public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";

	
	public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/rajat-v-3b0685128/";
	public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/rajatt95";
	public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
			+ "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
	public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
			+ "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";
	/* ICONS - END */
	
	
	/** Zip file of Extent Reports */
	private static final String Zipped_ExtentReports_Folder_Name = "ExtentReports.zip";

	private static final String Project_Name = "Automation Test Suite Report - Master RestAssured Framework";

	public static String getProjectPath() {
		return PROJECT_PATH;
	}

	public static String getProjectName() {
		return Project_Name;
	}

	public static String getZipped_ExtentReports_Folder_Name() {
		return Zipped_ExtentReports_Folder_Name;
	}

	public static String getYes() {
		return YES;
	}

	public static String getNo() {
		return NO;
	}

	public static String getExtentReportName() {
		return EXTENT_REPORT_NAME;
	}

	public static String getExtentReportFolderPath() {
		return EXTENT_REPORT_FOLDER_PATH;
	}

	public static String getExtentReportFilePath() {

		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = createReportPath();
		}
		return extentReportFilePath;
	}

	private static String createReportPath() {
		// if
		// (PropertyUtils.get(ConfigProperties.OVERRIDE_REPORTS).equalsIgnoreCase("no"))
		// {
		if (ConfigLoader.getInstance().getOverrideReports().equalsIgnoreCase(getNo())) {

			/*
			 * Report name -> Windows_10_Tue_Oct_05_02_30_46_IST_2021_AutomationReport.html
			 */
			return EXTENT_REPORT_FOLDER_PATH + OSInfoUtils.getOSInfo() + "_" + getCurrentDate() + "_"
					+ getExtentReportName();

		} else {
			return EXTENT_REPORT_FOLDER_PATH + getExtentReportName();
		}
	}

	private static String getCurrentDate() {
		Date date = new Date();
		return date.toString().replace(":", "_").replace(" ", "_");
	}

	public static void setExtentReportFilePath(String extentReportFilePath) {
		FrameworkConstants.extentReportFilePath = extentReportFilePath;
	}
}
