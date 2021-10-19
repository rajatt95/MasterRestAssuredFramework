package com.spotify.oauth2.utils;

import java.util.Properties;

import com.spotify.oauth2.api.enums.EnvType;

/*Singleton Design pattern*/
public class ConfigLoader {

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

	private static final String ENV = "env";
	private static final String CONFIG_PROPERTIES = "_config.properties";

	/* Default config file is stg_config.properties */
	private static final String STG_CONFIG_PROPERTIES = "stg" + CONFIG_PROPERTIES;
	private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
	private static final String QA_CONFIG_PROPERTIES = "qa" + CONFIG_PROPERTIES;
	private static final String INT_CONFIG_PROPERTIES = "int" + CONFIG_PROPERTIES;


	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
	private Properties properties;
	// private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {

		/* Setting EnvType.STAGE as default environment */
		/*
		 * This will check for the env value from Jenkins/Maven first. If it does not
		 * get any input from Jenkins/mvn cmd line, then, will take
		 * stg_config.properties file as default
		 */
		String env = System.getProperty(ENV, EnvType.STAGE.toString());

		switch (EnvType.valueOf(env)) {

		/* Only STAGE is working, Rest are taken for example */
		case STAGE: {
			properties = getConfigPropertyFile(STG_CONFIG_PROPERTIES);
			break;
		}
		case INT: {
			properties = getConfigPropertyFile(INT_CONFIG_PROPERTIES);
			break;
		}
		case QA: {
			properties = getConfigPropertyFile(QA_CONFIG_PROPERTIES);
			break;
		}
		case PRODUCTION: {
			properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
			break;
		}
		default: {
			throw new IllegalStateException("Invalid EnvType: " + env);
		}

		}
	}

	private Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getClientID() {
		return getPropertyValue(CLIENT_ID);
	}

	public String getClientSecret() {
		return getPropertyValue(CLIENT_SECRET);
	}

	public String getGrantType() {
		return getPropertyValue(GRANT_TYPE);
	}

	public String getRefreshToken() {
		return getPropertyValue(REFRESH_TOKEN);
	}

	public String getUserID() {
		return getPropertyValue(USER_ID);
	}

	public String getBaseUriAPI() {
		return getPropertyValue(BASE_URI_API);
	}

	public String getBaseUriAccounts() {
		return getPropertyValue(BASE_URI_ACCOUNTS);
	}

	public String getOverrideReports() {
		return getPropertyValue(OVERRIDE_REPORTS);
	}

	public String getRequestDetailsInReports() {
		return getPropertyValue(REQUEST_DETAILS_IN_REPORTS);
	}

	public String getSendEmailToUsers() {
		return getPropertyValue(SEND_EMAIL_TO_USERS);
	}

	public String getRetryFailedTests() {
		return getPropertyValue(RETRY_FAILED_TESTS);
	}

}
