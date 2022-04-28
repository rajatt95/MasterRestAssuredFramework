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

package com.spotify.oauth2.tests;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.spotify.oauth2.annotations.FrameworkAnnotation;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.api.enums.AuthorType;
import com.spotify.oauth2.api.enums.CategoryType;
import com.spotify.oauth2.api.enums.StatusCode;
import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.requests.pojo.lombok.Error;
import com.spotify.oauth2.requests.pojo.lombok.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.VerificationManager;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends _BaseTest {

    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.NISHANT}, 
	category = { CategoryType.SMOKE,CategoryType.SANITY, CategoryType.REGRESSION })
    @Test(groups = {"SMOKE","SANITY","REGRESSION"},description = "should be able to create a playlist - Description from TestNG")
    public void ShouldBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201,"User should be able to create a Playlist");
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @FrameworkAnnotation(author = { AuthorType.GAUTAM, AuthorType.PANKAJ}, 
			category = { CategoryType.SMOKE,CategoryType.REGRESSION })
	@Test(groups = {"SMOKE","REGRESSION"})
    public void ShouldBeAbleToGetAPlaylist(){
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().get_GetPlaylistID());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200,"User should be able to get the Playlist details");
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.NISHANT}, 
			category = { CategoryType.BVT,CategoryType.SANITY,CategoryType.REGRESSION })
	@Test(groups = {"BVT","SANITY","REGRESSION"})
    public void ShouldBeAbleToUpdateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.update(DataLoader.getInstance().get_UpdatePlaylistID(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200,"User should be able to update a Playlist");
    }

    @Story("Create a playlist story")
    @FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.NISHANT}, 
	category = { CategoryType.BVT,CategoryType.REGRESSION })
    @Test(groups = {"BVT","REGRESSION"})
    public void ShouldNotBeAbleToCreateAPlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400, "User should not be able to create a Playlist without Name");
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a playlist story")
    @FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.GAUTAM}, 
	category = { CategoryType.BVT,CategoryType.REGRESSION })
    @Test(groups = {"BVT","REGRESSION"})
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401, "User should not be able to create a Playlist with Expired Token");
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
		/*
		 * return Playlist.builder(). name(name). description(description).
		 * _public(_public). build();
		 */
        
        
		  Playlist playlist = new Playlist(); 
		  playlist.setName(name);
		  playlist.setDescription(description); 
		  playlist.set_public(_public); 
		  return playlist;
		 
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
        
        //VerificationManager.validateResponse(responsePlaylist.getName(),requestPlaylist.getName(), 
    	//FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD +" - <b> <u> "+message+" </u> </b>");
        VerificationManager.validateResponse(responsePlaylist.getName(),requestPlaylist.getName(),
        		FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> NAME </u> </b>");
        VerificationManager.validateResponse(responsePlaylist.getDescription(),requestPlaylist.getDescription(),
        		FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> DESCRIPTION </u> </b>");
        VerificationManager.validateResponse(responsePlaylist.get_public(),requestPlaylist.get_public(),
        		FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> PUBLIC </u> </b>");
	
        
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode, String message){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    	VerificationManager.validateResponse(actualStatusCode,statusCode.code, 
    			//FrameworkConstants.ASSERTION_FOR_RESPONSE_STATUS_CODE +" - <b> <u> Register the account </u> </b>");
    	FrameworkConstants.ASSERTION_FOR_RESPONSE_STATUS_CODE +" - <b> <u> "+message+" </u> </b>");
	
    }

    @Step
    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
        
        VerificationManager.validateResponse(responseErr.getError().getStatus(),statusCode.code,
        		FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> STATUS </u> </b>");
        VerificationManager.validateResponse(responseErr.getError().getMessage(),statusCode.msg,
        		FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> MSG </u> </b>");
      
        
    }
}
