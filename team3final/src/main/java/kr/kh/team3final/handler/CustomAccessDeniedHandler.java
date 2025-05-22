package kr.kh.team3final.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 현재 요청 URL 저장
        String uri = request.getRequestURI();
        request.getSession().setAttribute("denied-prev-page", uri);

        // 권한 오류 페이지로 리다이렉트
        response.sendRedirect("/error/denied");
    }
}
