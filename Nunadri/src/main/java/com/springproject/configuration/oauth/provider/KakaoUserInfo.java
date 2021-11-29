package com.springproject.configuration.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes; // oAuth2User.getAuthorities()
	private Map<String, Object> properties;

	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.properties = (Map<String, Object>) attributes.get("kakao_account");
	}

	@Override
	public String getProviderId() {
		// 카카오는 받아오는 id값이 int
		int id = (Integer) attributes.get("id");
		String id2 = String.valueOf(id);
		return id2;
	}

	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getEmail() {
		return (String) properties.get("email");
	}

	@Override
	public String getName() {
		Map profile = (Map) properties.get("profile");
		return (String) profile.get("nickname");
	}

	@Override
	public String getNickname() {
		Map profile = (Map) properties.get("profile");
		return (String) profile.get("nickname");
	}
}
