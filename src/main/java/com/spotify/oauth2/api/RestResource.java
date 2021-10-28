package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.getAccountRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import java.io.PrintStream;
import java.io.StringWriter;
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
	
		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		Response response = 
			//given().
				given(getAccountRequestSpec()).
					formParams(formParams).
					filter(new RequestLoggingFilter(captor)).
				when().
					//post("/api/token").
					post(API+TOKEN).
				then().
					spec(getResponseSpec()).
					extract().response();
		printDetailsInExtentReport(writerRequest, response);
		return response;
		
	}

	public static Response post(String path, String accessToken, Object payLoad) {
		
		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		//String User_ID = "x0308yuzh4ykcgbei7wypx4xw";
		Response response = 
				given(getRequestSpec()).
					// body(payLoad).
					body(payLoad).
					//header("Authorization", "Bearer "+accessToken).
					auth().oauth2(accessToken).
					filter(new RequestLoggingFilter(captor)).
				when().
					//post("users/"+User_ID+"/playlists").
				post(path).
				then().
					spec(getResponseSpec()).
					extract().response();
		
		printDetailsInExtentReport(writerRequest, response);
		return response;
	}
	
	public static Response get(String path, String accessToken) {
		
		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		Response response =
				given(getRequestSpec()).
					//header("Authorization", "Bearer "+accessToken).
					auth().oauth2(accessToken).
					filter(new RequestLoggingFilter(captor)).
				when().
					//get("playlists/"+Playlist_ID).
					get(path).
				then().
					spec(getResponseSpec()).
					extract().
					response();
	
		printDetailsInExtentReport(writerRequest, response);
		return response;
	}
	
	public static Response put(String path, String accessToken, Object payLoad) {
		
		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		Response response =
			given(getRequestSpec()).
				body(payLoad).
				//header("Authorization", "Bearer "+accessToken).
				auth().oauth2(accessToken).
				filter(new RequestLoggingFilter(captor)).
			when().
				//put("playlists/"+playlistID).
				put(path).
			then().
				//spec(getResponseSpec()).
				//log().all().
				extract().response();
		 
		 printDetailsInExtentReport(writerRequest, response);
		 return response;
	}

	private static void printDetailsInExtentReport(StringWriter writer, Response response) {
		if (ConfigLoader.getInstance().getRequestDetailsInReports().equalsIgnoreCase(FrameworkConstants.getYes())) {
			ExtentLogger.info("<details><summary><i><font color=black> Request details: </font></i>" + "</summary>"
					+ "<pre>" + writer.toString() + "</pre>" + "</details> \n");
			ExtentLogger.info("<details><summary><i><font color=black> Response details: </font></i>" + "</summary>"
					+ "<pre>" + response.asString() + "</pre>" + "</details> \n");
			ExtentLogger.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
		}
	}
	
}

