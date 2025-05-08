package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MainController {
	
	@GetMapping("/")
	public String main() {
		return "index";
	}
	
	@GetMapping("/reserv")
	public String reserv() {
		return "reserv";
	}
	
}
