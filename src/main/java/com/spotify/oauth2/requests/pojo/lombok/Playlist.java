
package com.spotify.oauth2.requests.pojo.lombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
/**
 * No need of private access modifier with every field
 * 
 * private Boolean collaborative; Boolean collaborative;
 */
/* Implement without Builder */
//@Data
//@Getter @Setter
/* Implement with Builder */
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {
	@JsonProperty("collaborative")
	Boolean collaborative;
	@JsonProperty("description")
	String description;
	@JsonProperty("external_urls")
	ExternalUrls externalUrls;
	@JsonProperty("followers")
	Followers followers;
	@JsonProperty("href")
	String href;
	@JsonProperty("id")
	String id;
	@JsonProperty("images")
	List<Object> images;
	@JsonProperty("name")
	String name;
	@JsonProperty("owner")
	Owner owner;
	@JsonProperty("primary_color")
	Object primaryColor;
	@JsonProperty("public")
	Boolean _public;
	@JsonProperty("snapshot_id")
	String snapshotId;
	@JsonProperty("tracks")
	Tracks tracks;
	@JsonProperty("type")
	String type;
	@JsonProperty("uri")
	String uri;
	
	public void set_public(Boolean _public) {
		this._public = _public;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Boolean get_public() {
		return _public;
	}
	/*
	 * public void set_public(Boolean _public) { this._public = _public; } public
	 * void setDescription(String description) { this.description = description; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getDescription() { return description; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public Boolean get_public() { return _public; }
	 * 
	 */	
}
