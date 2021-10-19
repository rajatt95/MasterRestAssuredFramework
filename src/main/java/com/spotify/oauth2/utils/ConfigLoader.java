package com.spotify.oauth2.utils;

import com.spotify.oauth2.api.enums.EnvType;

import java.util.Properties;

/*Singleton Design pattern*/
public class ConfigLoader {

    /* Default config file is stg_config.properties*/
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
    private final Properties properties;
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
        return getValueOfKey(CLIENT_ID);
    }

    public String getClientSecret() {
        return getValueOfKey(CLIENT_SECRET);
    }

    public String getGrantType() {
        return getValueOfKey(GRANT_TYPE);
    }

    public String getRefreshToken() {
        return getValueOfKey(REFRESH_TOKEN);
    }

    public String getUserID() {
        return getValueOfKey(USER_ID);
    }

    public String getBaseUriAPI() {
        return getValueOfKey(BASE_URI_API);
    }

    public String getBaseUriAccounts() {
        return getValueOfKey(BASE_URI_ACCOUNTS);
    }

    public String getOverrideReports() {
        return getValueOfKey(OVERRIDE_REPORTS);
    }

    public String getRequestDetailsInReports() {
        return getValueOfKey(REQUEST_DETAILS_IN_REPORTS);
    }

    public String getSendEmailToUsers() {
        return getValueOfKey(SEND_EMAIL_TO_USERS);
    }

    public String getRetryFailedTests() {
        return getValueOfKey(RETRY_FAILED_TESTS);
    }

    private String getValueOfKey(String key) {
        String prop = properties.getProperty(key);
        if (prop != null) {
            return prop.trim();
        } else {
            throw new RuntimeException(
                    "Property " + key + " is not specified in the config.properties file");
        }
    }

}


