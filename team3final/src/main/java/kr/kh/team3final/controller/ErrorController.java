package kr.kh.team3final.controller;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/error")
public class ErrorController {
	@GetMapping("/denied")
	public String accessDenied(HttpServletRequest request, Model model) {

		String msg = (String) request.getAttribute("msg");
		String url = (String) request.getAttribute("url");

		if (url == null) url = "/";

		System.out.println(url);
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "msg";
	}
}
