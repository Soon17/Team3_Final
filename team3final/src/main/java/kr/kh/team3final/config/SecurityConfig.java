package kr.kh.team3final.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import kr.kh.team3final.handler.CustomAccessDeniedHandler;
import kr.kh.team3final.model.vo.UserRole;
import kr.kh.team3final.service.CustomOAuth2UserService;
import kr.kh.team3final.service.MemberDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	MemberDetailService memberDetailService;
	// @Value("${spring.remember.me.key}")
	// String rememberMeKey;

	@Autowired
	CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf
				.disable())
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/admin/**")
						.hasRole(UserRole.ADMIN.name())
						.requestMatchers("/owner/**")
						.hasRole(UserRole.OWNER.name())
						.requestMatchers("/pay/**")
						.hasRole(UserRole.USER.name())
						.anyRequest()
						.permitAll() // 그 외 요청은 인증 필요
				)
				.formLogin((form) -> form
						.loginPage("/member/signIn") // 생략시 기본 로그인 페이지를 출력.
						.permitAll()
						.loginProcessingUrl("/logInPost") // 어느 페이지를 사용할 건지
						.defaultSuccessUrl("/") // 성공 시 이동할 페이지
				)
				.exceptionHandling(ex -> ex
					.accessDeniedHandler(customAccessDeniedHandler) // 여기에 적용
				)
				
				.oauth2Login(oauth -> oauth
						.loginPage("/member/signIn") // 커스텀 로그인 페이지
						.userInfoEndpoint(userInfo -> userInfo
								.userService(customOAuth2UserService))// 사용자 정보 처리
						.defaultSuccessUrl("/") // 로그인 성공 후 이동
				)
				// 자동 로그인 처리
				// .rememberMe(rm -> rm
				// .userDetailsService(memberDetailService)// 자동 로그인할 때 사용할 userDetailService를
				// 추가
				// .key(rememberMeKey)// 키가 변경되면 기존 토큰이 무효처리
				// .rememberMeCookieName("LC")// 쿠키 이름
				// .tokenValiditySeconds(60 * 60 * 24 * 7)// 유지 기간 : 7일
				// )
				.logout((logout) -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.clearAuthentication(true)
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()); // 로그아웃도 모두 접근 가능
		return http.build();
	}
}