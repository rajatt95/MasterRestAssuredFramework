package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.Route.BASE_PATH;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().
				//setBaseUri("https://api.spotify.com").
				setBaseUri(ConfigLoader.getInstance().getBaseUriAPI()).
				//setBasePath("/v1").
				setBasePath(BASE_PATH).
				/*Removing this header for Authorization as I have negative scenarios as well*/
				//addHeader("Authorization", "Bearer "+accessToken).
				setContentType(ContentType.JSON).
				log(LogDetail.ALL).
				//log(LogDetail.URI).
				build();
	}
	
	
	public static RequestSpecification getAccountRequestSpec() {
		return new RequestSpecBuilder().
				//setBaseUri("https://accounts.spotify.com").
				setBaseUri(ConfigLoader.getInstance().getBaseUriAccounts()).
				setContentType(ContentType.URLENC).
				log(LogDetail.ALL).
				build();
				
	}
	
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().
				//expectStatusCode(200).
				expectContentType(ContentType.JSON).
				log(LogDetail.ALL).
				build();
	}
}
