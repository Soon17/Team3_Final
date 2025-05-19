package kr.kh.team3final.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        // 유저를 가져옴
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes;

        if ("kakao".equals(provider)) {
            attributes = oauth2User.getAttributes();
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            String id = String.valueOf(attributes.get("id"));
            String name = (String) profile.get("nickname");
            String email = (String) kakaoAccount.get("email");

            // 필드 추출 출력
            System.out.println("✅ 카카오 로그인");
            System.out.println("ID: " + id);
            System.out.println("닉네임: " + name);
            System.out.println("이메일: " + email);

            // 필요한 정보만 attributes로 새롭게 구성하고 리턴
            Map<String, Object> customAttributes = Map.of(
                    "id", id,
                    "name", name,
                    "email", email
            );

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    customAttributes,
                    "id"
            );
        }

        else if ("naver".equals(provider)) {
            // 네이버는 사용자 정보를 "response"라는 키에 담아서 가져 옴
            attributes = oauth2User.getAttribute("response");

            String id = (String) attributes.get("id");
            String name = (String) attributes.get("name");
            String email = (String) attributes.get("email");

            // TODO: 사용자 정보로 원하는 작업 수행 (DB 저장, 세션 저장 등)
            System.out.println("✅ 네이버 로그인");
            System.out.println("ID: " + id);
            System.out.println("이름: " + name);
            System.out.println("이메일: " + email);

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    attributes,
                    "id" // 사용자의 고유 식별자 키
            );
        }

        // 다른 OAuth 제공자는 기본 처리
        return oauth2User;
    }
}
