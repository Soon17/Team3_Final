package kr.kh.team3final.controller;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/error")
public class ErrorController {
	@GetMapping("/denied")
	public String deniedError(HttpServletRequest request, Model model) {

		String redirectUrl = "/"; // 기본 경로

		HttpSession session = request.getSession(false);
		System.out.println("세션: " + session);
		if (session != null) {
			Object uriObj = session.getAttribute("denied-prev-page");
			System.out.println("uri: " + uriObj);
			if (uriObj != null) {
				String uri = uriObj.toString();

				// 무한 루프 방지: 다시 접근할 경우를 막음
				if (!uri.contains("/error/") && !uri.contains("/pay")) {
					redirectUrl = uri;
				}

				session.removeAttribute("denied-prev-page"); // 사용 후 제거
			}
		}

		model.addAttribute("msg", "일반 회원만 사용 가능한 서비스입니다.");
		model.addAttribute("url", redirectUrl);

		return "msg";
	}
}
