package kr.kh.team3final.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.kh.team3final.model.vo.MemberVO;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        // 유저를 가져옴
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes;

        if ("kakao".equals(provider)) {
            attributes = oauth2User.getAttributes();
            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            String id = String.valueOf(attributes.get("id"));
            String name = (String) kakaoAccount.get("name");
            String nickname = (String) properties.get("nickname");
            String email = (String) kakaoAccount.get("email");
            String birthday = (String) kakaoAccount.get("birthday");
            String birthyear = (String) kakaoAccount.get("birthyear");
            String birth = birthyear + birthday;
            String phoneNumber = (String) kakaoAccount.get("phone_number");
            phoneNumber = convertPhoneNumber(phoneNumber);
            String gender = (String) kakaoAccount.get("gender");
            if (gender.equals("male"))
                gender = "남자";
            if (gender.equals("female"))
                gender = "여자";
            String profileImage = (String) profile.get("profile_image_url");

            // 필드 추출 출력
            System.out.println("# 카카오 로그인");
            System.out.println("ID: " + id);
            System.out.println("이름: " + name);
            System.out.println("닉네임: " + nickname);
            System.out.println("이메일: " + email);
            System.out.println("생일: " + birth);
            System.out.println("성: " + gender);
            System.out.println("번호: " + phoneNumber);
            System.out.println("프로필사진: " + profileImage);

            MemberVO dbUser = memberService.getMemberByEmail(email);

            if (dbUser == null) {
                MemberVO newUser = new MemberVO();
                newUser.setMe_id(id);
                newUser.setMe_name(name);
                newUser.setMe_nick(nickname);
                newUser.setMe_pw("kakao");
                newUser.setMe_email(email);
                newUser.setMe_birthday(birth);
                newUser.setMe_gender(gender);
                newUser.setMe_number(phoneNumber);
                newUser.setMe_profile(profileImage);

                boolean insertMemberByIp = memberService.insertMemberByIp(newUser);
            }

            // 필요한 정보만 attributes로 새롭게 구성하고 리턴
            Map<String, Object> customAttributes = Map.of(
                    "id", id,
                    "name", name,
                    "nickname", nickname,
                    "email", email,
                    "birthday", birthday,
                    "birthyear", birthyear,
                    "gender", gender,
                    "phoneNumber", phoneNumber,
                    "profileImage", profileImage);

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    customAttributes,
                    "id");
        }

        else if ("naver".equals(provider)) {
            // 네이버는 사용자 정보를 "response"라는 키에 담아서 가져 옴
            attributes = oauth2User.getAttribute("response");

            String id = (String) attributes.get("id");
            String name = (String) attributes.get("name");
            String nickname = (String) attributes.get("nickname");
            String email = (String) attributes.get("email");
            String birthday = (String) attributes.get("birthday");
            String birthyear = (String) attributes.get("birthyear");
            String gender = (String) attributes.get("gender");
            String phoneNumber = (String) attributes.get("mobile");
            if (gender.equals("M"))
                gender = "남자";
            if (gender.equals("W"))
                gender = "여자";
            String profileImage = (String) attributes.get("profile_image");

            // TODO: 사용자 정보로 원하는 작업 수행 (DB 저장, 세션 저장 등)
            System.out.println("# 네이버 로그인");
            System.out.println("ID: " + id);
            System.out.println("이름: " + nickname);
            System.out.println("닉네임: " + name);
            System.out.println("이메일: " + email);
            System.out.println("생일: " + birthday);
            System.out.println("생년: " + birthyear);
            System.out.println("성: " + gender);
            System.out.println("번호: " + phoneNumber);
            System.out.println("프로필사진: " + profileImage);

            // 필요한 정보만 attributes로 새롭게 구성하고 리턴
            Map<String, Object> customAttributes = Map.of(
                    "id", id,
                    "name", name,
                    "nickname", nickname,
                    "email", email,
                    "birthday", birthday,
                    "birthyear", birthyear,
                    "gender", gender,
                    "phoneNumber", phoneNumber,
                    "profileImage", profileImage);

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    customAttributes,
                    "id" // 사용자의 고유 식별자 키
            );
        }

        // 다른 OAuth 제공자는 기본 처리
        return oauth2User;
    }

    public String convertPhoneNumber(String phone) {
        return phone.replace("+82", "0").replaceAll(" ", "");
    }
}
