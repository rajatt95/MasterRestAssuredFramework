package com.spotify.oauth2.utils;

import java.util.Properties;

import com.spotify.oauth2.api.enums.EnvType;

/*Singleton Design pattern*/
public class ConfigLoader {

	/* Default congi file is stg_config.properties*/
	private static final String STG_CONFIG_PROPERTIES = "stg_config.properties";
	private static final String PROD_CONFIG_PROPERTIES = "prod_config.properties";
	private static final String QA_CONFIG_PROPERTIES = "qa_config.properties";
	private static final String INT_CONFIG_PROPERTIES = "int_config.properties";
	
	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String GRANT_TYPE = "grant_type";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String USER_ID = "user_id";

	private static final String BASE_URI_API = "base_uri_api";
	private static final String BASE_URI_ACCOUNTS = "base_uri_accounts";

	private static final String OVERRIDE_REPORTS = "override_reports";
	private static final String REQUEST_DETAILS_IN_REPORTS = "request_details_in_reports";
	private static final String SEND_EMAIL_TO_USERS = "send_email_to_users";
	private static final String RETRY_FAILED_TESTS = "retry_failed_tests";
	
	
	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
	private Properties properties;
	// private final Properties properties;
	private static ConfigLoader configLoader;

 	private ConfigLoader() {

		/* Setting EnvType.STAGE as default environment */
		/*
		 * This will check for the env value from Jenkins/Maven first. If it does not get any
		 * input from Jenkins/mvn cmd line, then, will take stg_config.properties file as
		 * default
		 */
		String env = System.getProperty("env", EnvType.STAGE.toString());

		switch (EnvType.valueOf(env)) {
		/* Only STAGE is working, Rest are taken for example */
		case STAGE: {
			properties = PropertyUtils.propertyLoader(RESOURCES_PATH + STG_CONFIG_PROPERTIES);
			break;
		}
		case INT: {
			properties = PropertyUtils.propertyLoader(RESOURCES_PATH + INT_CONFIG_PROPERTIES);
			break;
		}
		case QA: {
			properties = PropertyUtils.propertyLoader(RESOURCES_PATH + QA_CONFIG_PROPERTIES);
			break;
		}
		case PRODUCTION: {
			properties = PropertyUtils.propertyLoader(RESOURCES_PATH + PROD_CONFIG_PROPERTIES);
			break;
		}
		default: {
			throw new IllegalStateException("Invalid EnvType: " + env);
		}

		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}


	public String getClientID() {
		String prop = properties.getProperty(CLIENT_ID);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+CLIENT_ID+" is not specified in the config.properties file");
		}
	}
	
	public String getClientSecret() {
		String prop = properties.getProperty(CLIENT_SECRET);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+CLIENT_SECRET+" is not specified in the config.properties file");
		}
	}
	
	public String getGrantType() {
		String prop = properties.getProperty(GRANT_TYPE);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+GRANT_TYPE+" is not specified in the config.properties file");
		}
	}
	
	public String getRefreshToken() {
		String prop = properties.getProperty(REFRESH_TOKEN);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+REFRESH_TOKEN+" is not specified in the config.properties file");
		}
	}
	
	public String getUserID() {
		String prop = properties.getProperty(USER_ID);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+USER_ID+" is not specified in the config.properties file");
		}
	}
	
	public String getBaseUriAPI() {
		String prop = properties.getProperty(BASE_URI_API);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+BASE_URI_API+" is not specified in the config.properties file");
		}
	}
	public String getBaseUriAccounts() {
		String prop = properties.getProperty(BASE_URI_ACCOUNTS);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+BASE_URI_ACCOUNTS+" is not specified in the config.properties file");
		}
	}
	
	
	public String getOverrideReports() {
		String prop = properties.getProperty(OVERRIDE_REPORTS);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+OVERRIDE_REPORTS+" is not specified in the config.properties file");
		}
	}

	
	public String getRequestDetailsInReports() {
		String prop = properties.getProperty(REQUEST_DETAILS_IN_REPORTS);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+REQUEST_DETAILS_IN_REPORTS+" is not specified in the config.properties file");
		}
	}
	
	
	public String getSendEmailToUsers() {
		String prop = properties.getProperty(SEND_EMAIL_TO_USERS);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property "+SEND_EMAIL_TO_USERS+" is not specified in the config.properties file");
		}
	}

	public String getRetryFailedTests() {
		String prop = properties.getProperty(RETRY_FAILED_TESTS);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException(
					"Property "+RETRY_FAILED_TESTS+" is not specified in the config.properties file");
		}
	}

	
}


