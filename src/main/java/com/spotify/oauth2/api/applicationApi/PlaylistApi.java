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

package com.spotify.oauth2.api.applicationApi;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.requests.pojo.lombok.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistApi {

	// static String ACCESS_TOKEN =
	// "BQAXDD8IlvaV1J47Xwqcl2tUKmS5nedDqmsfz_0nFuSLU1keTFdfp5PLFcdJukSbvPW8d7VQe9iN0VrkujuqZM1kZdpsrGkJT1WJhAIyToO_fjcXaGylcp9Oj6kJcVeP8IHYHCQrbITq_NQzQ7j4_snOq_5bSntnNNo5w7MSsc0goiiLxt8RCgDyke7gxUrgcmzNP0yRRd1CdzQet6xQY8krH89ioGzR_SoV39YZYha8";
	@Step
	public static Response post(Playlist requestPlaylist) {
		//String User_ID = "x0308yuzh4ykcgbei7wypx4xw";
		String User_ID = ConfigLoader.getInstance().getUserID();
		
		// return
		// RestResource.post("users/"+User_ID+"/playlists",ACCESS_TOKEN,requestPlaylist);
		return RestResource.post(USERS +"/"+ User_ID + PLAYLISTS, getToken(), requestPlaylist);
	}

	public static Response post(String payLoad) {
		//String User_ID = "x0308yuzh4ykcgbei7wypx4xw";
		String User_ID = ConfigLoader.getInstance().getUserID();
		// return RestResource.post("users/"+User_ID+"/playlists",getToken(),payLoad);
		return RestResource.post(USERS +"/"+ User_ID + PLAYLISTS, getToken(), payLoad);
	}

	public static Response post(String accessToken, String payLoad) {
		//String User_ID = "x0308yuzh4ykcgbei7wypx4xw";
		String User_ID = ConfigLoader.getInstance().getUserID();
		//return RestResource.post("users/" + User_ID + "/playlists", accessToken, payLoad);
		return RestResource.post(USERS +"/"+ User_ID + PLAYLISTS, accessToken, payLoad);
	}

	public static Response get(String Playlist_ID) {
		//return RestResource.get("playlists/" + Playlist_ID, getToken());
		return RestResource.get(PLAYLISTS +"/"+ Playlist_ID, getToken());
	}

	public static Response put(String Playlist_ID, String payLoad) {
		//return RestResource.put("playlists/" + Playlist_ID, getToken(), payLoad);
		return RestResource.put(PLAYLISTS +"/"+ Playlist_ID, getToken(), payLoad);
	}
	
	

   

    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserID()
                + PLAYLISTS, token, requestPlaylist);
    }

   
    public static Response update(String playlistId, Playlist requestPlaylist){
        return RestResource.put(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }

}
