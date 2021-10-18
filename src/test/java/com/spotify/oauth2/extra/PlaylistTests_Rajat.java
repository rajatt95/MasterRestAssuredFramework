package com.spotify.oauth2.extra;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.utils.DataLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PlaylistTests_Rajat {

	@Test
	public void shouldBeAbleToCreatePlaylist() {
		String payLoad="{\r\n"
				+ "  \"name\": \"New Playlist\",\r\n"
				+ "  \"description\": \"New playlist description\",\r\n"
				+ "  \"public\": false\r\n"
				+ "}"; 
		
	//	GetPlaylist requestPlaylist = new GetPlaylist();
	//	requestPlaylist.setName("New Playlist");
	//	requestPlaylist.setDescription("New Playlist Description");
	//	requestPlaylist.set_public(false);

		Response response = PlaylistApi.post(payLoad);
		
		
		JsonPath jsonPath = new JsonPath(response.asString());			
		Assert.assertEquals(response.getStatusCode(), 201,"Assertion for Response Status Code");
		System.out.println("jsonPath.getString(\"id\"): "+jsonPath.getString("id"));
		System.out.println("jsonPath.getString(\"name\"): "+jsonPath.getString("name"));
		System.out.println("jsonPath.getString(\"description\"): "+jsonPath.getString("description"));
		System.out.println("jsonPath.getString(\"owner.display_name\"): "+jsonPath.getString("owner.display_name"));
		
		//GetPlaylist responseGetPlaylist = response.as(GetPlaylist.class);
		
		Assert.assertEquals(response.getStatusCode(), 201,"Assertion for Response Status Code");

//		Assert.assertEquals(responseGetPlaylist.getName(), requestPlaylist.getName());
//		Assert.assertEquals(responseGetPlaylist.getDescription(), requestPlaylist.getDescription());
//		Assert.assertEquals(responseGetPlaylist.get_public(), requestPlaylist.getPublic());
//		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
	}

	@Test
	public void shouldBeAbleToGetPlaylist() {
		
		//Response response = PlaylistApi.get("66ogA2UdD3fMkFzySDO5wW");
		Response response = PlaylistApi.get(DataLoader.getInstance().get_GetPlaylistID());
		
		JsonPath jsonPath = new JsonPath(response.asString());
			
		Assert.assertEquals(response.getStatusCode(), 200,"Assertion for Response Status Code");
		System.out.println("jsonPath.getString(\"id\"): "+jsonPath.getString("id"));
		System.out.println("jsonPath.getString(\"name\"): "+jsonPath.getString("name"));
		System.out.println("jsonPath.getString(\"description\"): "+jsonPath.getString("description"));
		System.out.println("jsonPath.getString(\"owner.display_name\"): "+jsonPath.getString("owner.display_name"));
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
	}


	@Test
	public void shouldBeAbleToUpdatePlaylist() {
		
		String payLoad="{\r\n"
				+ "  \"name\": \"Updated Playlist Name\",\r\n"
				+ "  \"description\": \"Updated playlist description\",\r\n"
				+ "  \"public\": false\r\n"
				+ "}";
		
		//Response response = PlaylistApi.put("66ogA2UdD3fMkFzySDO5wW",payLoad);
		Response response = PlaylistApi.put(DataLoader.getInstance().get_UpdatePlaylistID(), payLoad);
			
		Assert.assertEquals(response.getStatusCode(), 200,"Assertion for Response Status Code");
		System.out.println("response.getStatusCode(): "+response.getStatusCode());
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
		
	}

	
	@Test
	public void shouldNotBeAbleToCreatePlaylistWithoutName() {
		String payLoad="{\r\n"
				+ "  \"name\": \"\",\r\n"
				+ "  \"description\": \"New playlist description\",\r\n"
				+ "  \"public\": false\r\n"
				+ "}"; 
		
		Response response = PlaylistApi.post(payLoad);
			
		JsonPath jsonPath = new JsonPath(response.asString());
			
		Assert.assertEquals(response.getStatusCode(), 400,"Assertion for Response Status Code");
		System.out.println("jsonPath.getString(\"error.status\"): "+jsonPath.getString("error.status"));
		System.out.println("jsonPath.getString(\"error.message\"): "+jsonPath.getString("error.message"));
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
	}

	
	@Test
	public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {
		String payLoad="{\r\n"
				+ "  \"name\": \"Playlist name\",\r\n"
				+ "  \"description\": \"New playlist description\",\r\n"
				+ "  \"public\": false\r\n"
				+ "}"; 
		
		String invalidToken = "12345";
		
		Response response = PlaylistApi.post(invalidToken,payLoad);
		JsonPath jsonPath = new JsonPath(response.asString());
			
		Assert.assertEquals(response.getStatusCode(), 401,"Assertion for Response Status Code");
		System.out.println("jsonPath.getString(\"error.status\"): "+jsonPath.getString("error.status"));
		System.out.println("jsonPath.getString(\"error.message\"): "+jsonPath.getString("error.message"));
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
	}

	
	
}
