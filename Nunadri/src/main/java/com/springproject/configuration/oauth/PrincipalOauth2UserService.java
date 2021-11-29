package com.springproject.configuration.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.springproject.configuration.auth.PrincipalDetails;
import com.springproject.configuration.oauth.provider.FacebookUserInfo;
import com.springproject.configuration.oauth.provider.GoogleUserInfo;
import com.springproject.configuration.oauth.provider.KakaoUserInfo;
import com.springproject.configuration.oauth.provider.NaverUserInfo;
import com.springproject.configuration.oauth.provider.OAuth2UserInfo;
import com.springproject.role.Role;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	//임시 비밀번호 값으로 사용하기 위한 코스키 외부노출(X)
	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	@Lazy // 종속성 순환에러 오류로 인한 처리 어노테이션
	private PasswordEncoder encoder;

	@Autowired
	private MemberService memberservice;

	//구글 로그인 후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest : " + userRequest.getClientRegistration());
		System.out.println("userRequest : " + userRequest.getAccessToken());
		System.out.println("userRequest : " + super.loadUser(userRequest).getAttributes());

		OAuth2User oAuth2User = super.loadUser(userRequest);

		System.out.println("getAttributes :" + oAuth2User.getAuthorities());

		OAuth2UserInfo oAuth2UserInfo = null;

		// 소셜 로그인시 유저정보 가져오는 메소드
		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
			System.out.println("페이스북로그인 요청");
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
			System.out.println("네이버로그인 요청");
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
			System.out.println("카카오로그인 요청");
		} else {
			System.out.println("우린 구글,페이스북,네이버만,카카오만 지원합니다");
		}

		String provider = oAuth2UserInfo.getProvider();
		String providerid = oAuth2UserInfo.getProviderId();
		String id = providerid;
		String pwd = encoder.encode(cosKey);
		String name = oAuth2UserInfo.getName();
		String email = oAuth2UserInfo.getEmail();
		String nickname = oAuth2UserInfo.getNickname();

		// 미가입 회원시 회원가입 진행
		MemberVO socialMember = memberservice.findId(id);
		if (socialMember == null) {
			System.out.println("소셜로그인이 최초입니다.");
			socialMember = MemberVO.builder()
							.id(id)
							.pwd(pwd)
							.name(name)
							.nickname(nickname)
							.email(email)
							.role(Role.USER)
							.provider(provider)
							.build();
			memberservice.join(socialMember);
		} else {
			System.out.println("소셜로그인을 이미 한적이 있습니다. 당신은 자동 회원가입이 되어있어요");
		}

		return new PrincipalDetails(socialMember, oAuth2User.getAttributes());
	}
}
