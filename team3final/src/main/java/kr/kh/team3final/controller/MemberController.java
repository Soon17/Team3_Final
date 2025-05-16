package kr.kh.team3final.controller;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.team3final.model.vo.MemberVO;
import kr.kh.team3final.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;






@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;

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
	@GetMapping("/signIn")
	public String getMethodName() {
		return "member/signIn";
	}
	@GetMapping("/check/id")
	@ResponseBody
	public boolean checkId(@RequestParam String id) {
		return memberService.checkId(id);
	}
	
	@PostMapping("/signInPost")
	public String signIn(Model model, MemberVO member) {
		if(memberService.signIn(member)){
			model.addAttribute("msg", "회원가입이 완료되었습니다!");
		}
		else {
			model.addAttribute("msg", "회원가입에 실패하였습니다!");
		}
		model.addAttribute("url", "/member/signIn");
		
		return "msg";
	}
	
}
