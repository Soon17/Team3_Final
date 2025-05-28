package kr.kh.team3final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.team3final.dao.MemberDAO;
import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.dto.UpdateUserDTO;
import kr.kh.team3final.model.vo.MemberVO;
import kr.kh.team3final.model.vo.ReservationVO;
import kr.kh.team3final.service.MemberService;
import kr.kh.team3final.service.ReservationService;
import kr.kh.team3final.service.ReviewService;
import kr.kh.team3final.utils.CustomUser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberDAO memberDAO;

	@Autowired
	ReservationService reservationService;

	@Autowired
	ReviewService reviewService;

	@GetMapping("/mypage") // 마이 페이지
	public String mypage(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		if(user != null){
			model.addAttribute("user", user.getUser());
			model.addAttribute("logIn", true);
		}
		else if(oauth2user != null){
			MemberVO dbuser = memberDAO.selectMember(oauth2user.getName());
			model.addAttribute("user", dbuser);
			model.addAttribute("logIn", true);
		}
		else {
			model.addAttribute("logIn", false);
		}
		return "member/mypage";
	}

	@GetMapping("/reservation-history") // 마이 페이지 -> 예약내역 -> 호텔 탭
	public String reservation(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		if(user != null){
			int meNum = user.getUser().getMe_num();
			model.addAttribute("user", user.getUser());
			List<ReservationVO> list = reservationService.selectList(meNum);
			model.addAttribute("reservation", list);
			return "member/reservation-history";
		}
		else if(oauth2user != null){
			MemberVO dbuser = memberDAO.selectMember(oauth2user.getName());
			int meNum = dbuser.getMe_num();
			model.addAttribute("user", dbuser);
			List<ReservationVO> list = reservationService.selectList(meNum);
			model.addAttribute("reservation", list);
			return "member/reservation-history";
		}
		return "redirect:/member/signIn";
	}

	@GetMapping("/view-review")
	public String viewReview(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2User) {
		Integer meNum = null;
		if(user != null) {
			meNum = user.getUser().getMe_num();
		} else if(oauth2User != null) {
			Object meNumObj = oauth2User.getAttribute("meNum");
				if(meNumObj instanceof Integer) {
						meNum = (Integer) meNumObj;
				} else if(meNumObj instanceof String) {
						meNum = Integer.valueOf((String) meNumObj);
				}
		}

		List<Lodging_ReviewDTO> list = new ArrayList<>();
		if(meNum != null) {
			list = reviewService.getSelectReviewList(meNum);
		}
		
		model.addAttribute("list", list);
		return "member/view-review";
	}

	@GetMapping("/reservation-hotel")
	public String myHotel(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		if (user != null) {
			int meNum = user.getUser().getMe_num();
			model.addAttribute("user", user.getUser());
	
			List<ReservationVO> latestReservation = reservationService.getLatestReservation(meNum);
			model.addAttribute("reservations", latestReservation);
			model.addAttribute("logIn", false);
		}
		else if(oauth2user != null){
			MemberVO dbuser = memberDAO.selectMember(oauth2user.getName());

			int meNum = dbuser.getMe_num();
			model.addAttribute("user", dbuser);
	
			List<ReservationVO> latestReservation = reservationService.getLatestReservation(meNum);
			model.addAttribute("reservations", latestReservation);
			model.addAttribute("logIn", false);
		}

		model.addAttribute("logIn", false);
		return "member/reservation-hotel";
	}

	@GetMapping("/reservation-rent")
	public String myRent() {
		return "member/reservation-rent";
	}

	@GetMapping("/modify")
	public String modify(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
			
		MemberVO member = null;

		if (user != null) {
			member = user.getUser();
		}
		else if (oauth2user != null) {
			String email = (String) oauth2user.getAttributes().get("email");
			String provider = ((String) oauth2user.getAttributes().getOrDefault("provider", "NORMAL")).toUpperCase();
			member = memberService.getMemberByEmailAndProvider(email, provider);
		}
		else {
			return "redirect:/member/signIn";
		}

		model.addAttribute("user", member);

		String birthday = member.getMe_birthday();
		if (birthday != null && birthday.length() == 8) {
			String year = birthday.substring(0, 4);
			String month = birthday.substring(4, 6);
			String day = birthday.substring(6, 8);

			model.addAttribute("year", year);
			model.addAttribute("month", Integer.parseInt(month));
			model.addAttribute("day", Integer.parseInt(day));
		}

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
	public List<ReservationVO> getReservationHistory(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		if(user != null){
			int meNum = user.getUser().getMe_num();
			return reservationService.getLatestReservation(meNum);
		}
		else if(oauth2user != null){
			int meNum = (memberDAO.selectMember(oauth2user.getName())).getMe_num();
			return reservationService.getLatestReservation(meNum);
		}
		return new ArrayList<>();
	}

	@GetMapping("/reservation-list-ajax") // 숙소 예약 내역 전체 리스트
	@ResponseBody
	public List<ReservationVO> getReservationHistoryAjax(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		if(user != null){
			int meNum = user.getUser().getMe_num();
			return reservationService.selectList(meNum); // 전체 리스트 반환
		}
		else if(oauth2user != null){
			int meNum = (memberDAO.selectMember(oauth2user.getName())).getMe_num();
			return reservationService.selectList(meNum); // 전체 리스트 반환
		}
		return new ArrayList<>();
	}

	@PostMapping("/modify")
	@ResponseBody
	public String updateUser(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user, @RequestBody UpdateUserDTO dto) {
		MemberVO member = null;

		if(user != null){
			member = user.getUser();
		}
		else if(oauth2user != null){
			member = memberDAO.selectMember(oauth2user.getName());
		}
		else{
			return "redirect:/member/signIn";
		}

		member.setMe_number(dto.getMe_number());
    member.setMe_nick(dto.getMe_nick());
    member.setMe_birthday(dto.getMe_birthday());
    member.setMe_gender(dto.getMe_gender());

		boolean updateUser = memberService.updateUser(member);

		model.addAttribute("msg", updateUser ? "회원 정보가 성공적으로 수정되었습니다." : "회원 정보 수정에 실패했습니다.");
    model.addAttribute("url", "/member/mypage");
		
		return "msg";
	}
	
	@PostMapping("/delete")
	public String deleteMember(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		MemberVO member = null;

		if (user != null) {
				member = user.getUser();
		} else if (oauth2user != null) {
				String email = (String) oauth2user.getAttributes().get("email");
				String provider = (String) oauth2user.getAttributes().getOrDefault("provider", "NORMAL");
				member = memberService.getMemberByEmailAndProvider(email, provider);
		}

		if (member != null) {
				member.setMe_del("Y");
				memberService.updateMemberDel(member.getMe_num());
				SecurityContextHolder.clearContext(); // 로그아웃
		}

		return "redirect:/member/signIn";
	}
	
}
