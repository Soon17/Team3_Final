package kr.kh.team3final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.vo.MemberVO;
import kr.kh.team3final.model.vo.ReservationVO;
import kr.kh.team3final.service.MemberService;
import kr.kh.team3final.service.ReservationService;
import kr.kh.team3final.service.ReviewService;
import kr.kh.team3final.utils.CustomUser;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	ReservationService reservationService;

	@Autowired
	ReviewService reviewService;

	@GetMapping("/mypage") // 마이 페이지
	public String mypage(Model model, @AuthenticationPrincipal CustomUser user) {
		if(user != null){
			model.addAttribute("user", user.getUser());
			model.addAttribute("logIn", true);
		}
		else {
			model.addAttribute("logIn", false);
		}
		return "member/mypage";
	}

	@GetMapping("/reservation-history") // 마이 페이지 -> 예약내역 -> 호텔 탭
	public String reservation(Model model, @AuthenticationPrincipal CustomUser user) {
		if(user == null){
			return "redirect:/member/signIn";
		}
		int meNum = user.getUser().getMe_num();
		model.addAttribute("user", user.getUser());
		List<ReservationVO> list = reservationService.selectList(meNum);
		model.addAttribute("reservation", list);
		return "member/reservation-history";
	}

	@GetMapping("/view-review")
	public String view(Model model) {
		List<Lodging_ReviewDTO> list = reviewService.getSelectReviewList();
		if (list == null)
			list = new ArrayList<>();
		model.addAttribute("list", list);
		return "member/view-review";
	}

	@GetMapping("/reservation-hotel")
	public String myHotel(Model model, @AuthenticationPrincipal CustomUser user) {
		if (user == null) {
				// 비로그인 상태면 호텔 예약이 없다는 "HTML fragment"만 보여주기
				model.addAttribute("logIn", false);
				return "member/reservation-hotel"; // → 로그인 필요 메시지 포함된 fragment
		}

		int meNum = user.getUser().getMe_num();
		model.addAttribute("user", user.getUser());

		List<ReservationVO> latestReservation = reservationService.getLatestReservation(meNum);
		model.addAttribute("reservations", latestReservation);
		model.addAttribute("logIn", false); // 로그인 상태

		return "member/reservation-hotel"; // → 실제 예약 내역이 있는 fragment
	}

	@GetMapping("/reservation-rent")
	public String myRent() {
		return "member/reservation-rent";
	}

	@GetMapping("/modify")
	public String modify(Model model, @AuthenticationPrincipal CustomUser user) {
		model.addAttribute("user", user.getUser());
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

	@GetMapping("/reservation-history-ajax") // 숙소 예약 '제일 최신 일자' '예약완료' 내역 1개
	@ResponseBody
	public List<ReservationVO> getReservationHistory(@AuthenticationPrincipal CustomUser user) {
		if(user == null){
			return new ArrayList<>();
		}
		int meNum = user.getUser().getMe_num();
		return reservationService.getLatestReservation(meNum);
	}

	@GetMapping("/reservation-list-ajax") // 숙소 예약 내역 전체 리스트
	@ResponseBody
	public List<ReservationVO> getReservationHistoryAjax(@AuthenticationPrincipal CustomUser user) {
		if(user == null){
			return new ArrayList<>();
		}
		int meNum = user.getUser().getMe_num();
		return reservationService.selectList(meNum); // 전체 리스트 반환
	}
}
