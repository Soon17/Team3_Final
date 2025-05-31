package kr.kh.team3final.handler;

import java.io.IOException;
import java.net.URLEncoder;

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
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        System.out.println("이전 페이지 : " + prevPage);

        if (prevPage == null || prevPage.equals(request.getRequestURI())) {
            prevPage = "/";
        }

        String msg = URLEncoder.encode("접근 권한이 없습니다.", "UTF-8");
        String url = URLEncoder.encode(prevPage, "UTF-8");

        response.sendRedirect("/msg?msg=" + msg + "&url=" + url);
        
    }
}
