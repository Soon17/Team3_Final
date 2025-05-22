package kr.kh.team3final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String mypage() {
		return "member/mypage";
	}

	@GetMapping("/reservation-history") // 마이 페이지 -> 예약내역 -> 호텔 탭
	public String reservation(Model model) {
		List<ReservationVO> list = reservationService.selectList();
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

	@GetMapping("/reservation-hotel") // 마이 페이지 -> 호텔 탭
	public String myHotel(Model model) {
		List<ReservationVO> latestReservation = reservationService.getLatestReservation();
		model.addAttribute("reservations", latestReservation);
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

	@GetMapping("/reservation-history-ajax") // 숙소 예약 '제일 최신 일자' '예약완료' 내역 1개
	@ResponseBody
	public List<ReservationVO> getReservationHistory() {
		return reservationService.getLatestReservation();
	}

	@GetMapping("/reservation-list-ajax") // 숙소 예약 내역 전체 리스트
	@ResponseBody
	public List<ReservationVO> getReservationHistoryAjax() {
		return reservationService.selectList(); // 전체 리스트 반환
	}
}
