package me.kreal.attendance.config;

import me.kreal.attendance.DTO.ProfileDTO;

public class ZoomAPI extends ApiBinding {

	private static final String API_BASE_URL = "https://api.zoom.us/v2";

	public ZoomAPI(String accessToken) {
		super(accessToken);
	}

	public String test() {
		return "";
	}
	
	public ProfileDTO getProfile() {
		return restTemplate.getForObject(API_BASE_URL + "/users/me", ProfileDTO.class);
	}
//
//	public List<Post> getFeed() {
//		return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me/feed", Feed.class).getData();
//	}

}
