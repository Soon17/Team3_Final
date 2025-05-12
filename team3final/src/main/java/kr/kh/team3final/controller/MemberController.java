package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/mypage")
	public String mypage() {
		return "member/mypage";
	}
	@GetMapping("/reservation-history")
	public String reservation() {
		return "member/reservation-history";
	}
	@GetMapping("/view-review")
	public String view() {
		return "member/view-review";
	}
	@GetMapping("/reservation-hotel")
	public String myHotel() {
		return "member/reservation-hotel";
	}
	@GetMapping("/reservation-rent")
	public String myRent() {
		return "member/reservation-rent";
	}
	@GetMapping("/modify")
	public String modify() {
		return "member/modify";
	}
	@GetMapping("/singIn")
	public String getMethodName() {
		return "member/signIn";
	}
	
}
