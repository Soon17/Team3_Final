package kr.kh.team3final.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            String birth = convertBirthday(birthyear + birthday);
            String phoneNumber = (String) kakaoAccount.get("phone_number");
            phoneNumber = convertPhoneNumber(phoneNumber);
            String gender = (String) kakaoAccount.get("gender");
            gender = convertGender(gender);
            String profileImage = (String) profile.get("profile_image_url");

            // 필드 추출 출력
            // System.out.println("# 카카오 로그인");
            // System.out.println("ID: " + id);
            // System.out.println("이름: " + name);
            // System.out.println("닉네임: " + nickname);
            // System.out.println("이메일: " + email);
            // System.out.println("생일: " + birth);
            // System.out.println("성: " + gender);
            // System.out.println("번호: " + phoneNumber);
            // System.out.println("프로필사진: " + profileImage);

            return defaultOAuth2User(id, name, nickname, email, birth, gender, phoneNumber, profileImage, provider);
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
            String birth = convertBirthday(birthyear + birthday);
            String phoneNumber = (String) attributes.get("mobile");
            phoneNumber = convertPhoneNumber(phoneNumber);
            String gender = (String) attributes.get("gender");
            gender = convertGender(gender);
            String profileImage = (String) attributes.get("profile_image");

            // 필드 추출 출력
            // System.out.println("# 네이버 로그인");
            // System.out.println("ID: " + id);
            // System.out.println("이름: " + nickname);
            // System.out.println("닉네임: " + name);
            // System.out.println("이메일: " + email);
            // System.out.println("생일: " + birth);
            // System.out.println("성: " + gender);
            // System.out.println("번호: " + phoneNumber);
            // System.out.println("프로필사진: " + profileImage);

            return defaultOAuth2User(id, name, nickname, email, birth, gender, phoneNumber, profileImage, provider);
        }

        else if ("google".equals(provider)) {
            attributes = oauth2User.getAttributes();

            String id = (String) attributes.get("sub"); // Google은 "sub"를 사용자 고유 ID로 사용
            String name = (String) attributes.get("name");
            String nickname = (String) attributes.get("given_name"); // 또는 필요 시 name을 그대로 사용
            String email = (String) attributes.get("email");
            String profileImage = (String) attributes.get("picture");

            OAuth2AccessToken accessToken = userRequest.getAccessToken();
            String tokenValue = accessToken.getTokenValue();

            // 👉 People API 호출
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenValue);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> peopleResponse = restTemplate.exchange(
                    "https://people.googleapis.com/v1/people/me?personFields=birthdays,genders",
                    HttpMethod.GET,
                    entity,
                    Map.class);

            Map<String, Object> people = peopleResponse.getBody();

            String phoneNumber = ""; // 기본 정보에 없음

            String birth = ""; // Google은 생일 정보는 기본적으로 제공하지 않음
            List<Map<String, Object>> birthdays = (List<Map<String, Object>>) people.get("birthdays");
            if (birthdays != null && !birthdays.isEmpty()) {
                Map<String, Object> date = (Map<String, Object>) birthdays.get(0).get("date");
                birth = String.format("%04d%02d%02d", date.get("year"), date.get("month"), date.get("day"));
            }

            String gender = "";
            List<Map<String, Object>> genders = (List<Map<String, Object>>) people.get("genders");
            if (genders != null && !genders.isEmpty()) {
                gender = (String) genders.get(0).get("value");
            }
            gender = convertGender(gender);

            // 필드 추출 출력
            // System.out.println("# 구글 로그인");
            // System.out.println("ID: " + id);
            // System.out.println("이름: " + nickname);
            // System.out.println("닉네임: " + name);
            // System.out.println("이메일: " + email);
            // System.out.println("생일: " + birth);
            // System.out.println("성: " + gender);
            // System.out.println("번호: " + phoneNumber);
            // System.out.println("프로필사진: " + profileImage);

            return defaultOAuth2User(id, name, nickname, email, birth, gender, phoneNumber, profileImage, provider);
        }

        // 다른 OAuth 제공자는 기본 처리
        return oauth2User;
    }

    public String convertPhoneNumber(String phone) {
        if(phone == null){
            return "";
        }
        return phone.replace("+82", "0")
                .replaceAll(" ", "");
    }

    public String convertGender(String gender) {
        if(gender == null){
            return "";
        }
        if (gender.equals("M") || gender.equals("male"))
            return "남자";
        if (gender.equals("W") || gender.equals("female"))
            return "여자";
        else
            return "";
    }

    public String convertBirthday(String birthday) {
        if(birthday == null){
            return "";
        }
        return birthday.replaceAll("-", "");
    }

    public DefaultOAuth2User defaultOAuth2User(
        String id, String name, String nickname, String email, String birth,
        String gender, String phoneNumber, String profileImage, String provider) {

        // 1. 이메일 + provider로 회원 조회
        MemberVO dbUser = memberService.getMemberByEmailAndProvider(email, provider.toUpperCase());

        // 2. 없으면 새로 가입
        if (dbUser == null) {
            MemberVO newUser = new MemberVO();
            newUser.setMe_id(id);
            newUser.setMe_name(name);
            newUser.setMe_nick(nickname);
            newUser.setMe_pw(provider); // 임시 비밀번호
            newUser.setMe_email(email);
            newUser.setMe_birthday(birth);
            newUser.setMe_gender(gender);
            newUser.setMe_number(phoneNumber);
            newUser.setMe_profile(profileImage);
            newUser.setMe_provider(provider.toUpperCase());
            newUser.setMe_authority("USER");
            newUser.setMe_del("N");

            boolean success = memberService.insertMemberByIp(newUser);
            if (!success) {
                throw new RuntimeException("소셜 로그인 회원가입 실패");
            }

            dbUser = newUser;
        }

        // 3. 권한 설정
        if (dbUser.getMe_authority() == null) {
            dbUser.setMe_authority("USER");
        }
        String role = "ROLE_" + dbUser.getMe_authority();

        // 4. 사용자 정보 맵 구성
        Map<String, Object> customAttributes = Map.of(
            "id", dbUser.getMe_id(),
            "name", dbUser.getMe_name(),
            "nickname", dbUser.getMe_nick(),
            "email", dbUser.getMe_email(),
            "birthday", dbUser.getMe_birthday(),
            "gender", dbUser.getMe_gender(),
            "phoneNumber", dbUser.getMe_number(),
            "profileImage", dbUser.getMe_profile(),
            "provider", dbUser.getMe_provider(),
            "num", dbUser.getMe_num()
        );

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(role)),
            customAttributes,
            "id"
        );

    }
}
