package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import org.apache.commons.io.output.WriterOutputStream;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.reports.ExtentLogger;
import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

public class RestResource {

    public static Response postAccount(HashMap<String, String> formParams) {
        StringWriter writerRequest = new StringWriter();
        Response response =
                given(getAccountRequestSpec()).
                        formParams(formParams).
                        filter(requestLogging(writerRequest)).
                        when().
                        post(API + TOKEN).
                        then().
                        spec(getResponseSpec()).
                        extract().response();
        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response post(String path, String accessToken, Object payLoad) {
        StringWriter writerRequest = new StringWriter();
        Response response =
                given(getRequestSpec()).
                        body(payLoad).
                        auth().oauth2(accessToken).
                        filter(requestLogging(writerRequest)).
                        when().
                        post(path).
                        then().
                        spec(getResponseSpec()).
                        extract().response();
        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response get(String path, String accessToken) {
        StringWriter writerRequest = new StringWriter();
        Response response =
                given(getRequestSpec()).
                        auth().oauth2(accessToken).
                        filter(requestLogging(writerRequest)).
                        when().
                        get(path).
                        then().
                        spec(getResponseSpec()).
                        extract().
                        response();
        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response put(String path, String accessToken, Object payLoad) {
        StringWriter writerRequest = new StringWriter();
        Response response =
                given(getRequestSpec()).
                        body(payLoad).
                        auth().oauth2(accessToken).
                        filter(requestLogging(writerRequest)).
                        when().
                        put(path).
                        then().
                        extract().response();
        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    private static RequestLoggingFilter requestLogging(Writer writer) {
        PrintStream captor = new PrintStream(new WriterOutputStream(writer, UTF_8), true);
        return new RequestLoggingFilter(captor);
    }

    private static void printDetailsInExtentReport(StringWriter writer, Response response) {
        if (ConfigLoader.getInstance().getRequestDetailsInReports().equalsIgnoreCase(FrameworkConstants.YES)) {
            ExtentLogger.info("<details><summary><i><font color=black> Request details: </font></i>" + "</summary>"
                    + "<pre>" + writer.toString() + "</pre>" + "</details> \n");
            ExtentLogger.info("<details><summary><i><font color=black> Response details: </font></i>" + "</summary>"
                    + "<pre>" + response.asString() + "</pre>" + "</details> \n");
            ExtentLogger.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        }
    }

}

