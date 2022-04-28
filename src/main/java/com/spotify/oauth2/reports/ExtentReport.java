/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: REST Assured API Automation from scratch + Framework + CI (https://www.udemy.com/course/rest-assured-api-automation/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 * Tutor: Amuthan Sakthivel (https://www.testingminibytes.com/)
 */

/***************************************************/


package com.spotify.oauth2.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import static com.spotify.oauth2.constants.FrameworkConstants.ICON_LAPTOP;
import static com.spotify.oauth2.constants.FrameworkConstants.ICON_SOCIAL_GITHUB;
import static com.spotify.oauth2.constants.FrameworkConstants.ICON_SOCIAL_LINKEDIN;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.spotify.oauth2.api.enums.AuthorType;
import com.spotify.oauth2.api.enums.CategoryType;
import com.spotify.oauth2.constants.FrameworkConstants;

public final class ExtentReport {

	private ExtentReport() {
	}

	private static ExtentReports extent;

	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			/*
			 * .viewConfigurer() .viewOrder() .as(new ViewName[] { ViewName.DASHBOARD,
			 * ViewName.TEST, //ViewName.TAG, ViewName.CATEGORY, ViewName.AUTHOR,
			 * ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG }) .apply();
			 */

			/*
			 * You can even update the view of the ExtentRerport - Whta do you want to you
			 * first, you can prioritize
			 */
			/*
			 * ExtentSparkReporter spark = new
			 * ExtentSparkReporter(REPORTS_SPARK_CUSTOMISED_HTML).viewConfigurer().viewOrder
			 * () .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY
			 * }).apply();
			 */
			extent.attachReporter(spark);

			// spark.config().setEncoding("utf-8");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle(FrameworkConstants.getProjectName() + " - ALL");
			spark.config().setReportName(FrameworkConstants.getProjectName() + " - ALL");

//			extent.setSystemInfo("Organization", "Nagarro");
//			extent.setSystemInfo("Employee", "Rajat Verma");
//			extent.setSystemInfo("Domain", "Engineering (IT - Software)");
//			extent.setSystemInfo("Skill", "Test Automation Engineer");
			extent.setSystemInfo("Organization", "Nagarro");
			extent.setSystemInfo("Employee",
					"<b> Rajat Verma </b>" + " " + ICON_SOCIAL_LINKEDIN + " " + ICON_SOCIAL_GITHUB);
			extent.setSystemInfo("Domain", "Engineering (IT - Software)"+"  "+ICON_LAPTOP);
			extent.setSystemInfo("Skill", "Test Automation Engineer");
		}
	}

	public static void flushReports() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}

		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTest(String testCaseName) {
		ExtentManager.setExtentTest(extent.createTest(testCaseName));
	}

	synchronized public static void addAuthors(AuthorType[] authors) {
		for (AuthorType author : authors) {
			ExtentManager.getExtentTest().assignAuthor(author.toString());
		}
	}

	synchronized public static void addCategories(CategoryType[] categories) {
		for (CategoryType category : categories) {
			ExtentManager.getExtentTest().assignCategory(category.toString());
		}
	}

}
