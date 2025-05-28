package kr.kh.team3final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.team3final.dao.MemberDAO;
import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
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
	public String view(Model model) {
		List<Lodging_ReviewDTO> list = reviewService.getSelectReviewList();
		if (list == null)
			list = new ArrayList<>();
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
			model.addAttribute("logIn", false); // 로그인 상태
		}
		else if(oauth2user != null){
			MemberVO dbuser = memberDAO.selectMember(oauth2user.getName());

			int meNum = dbuser.getMe_num();
			model.addAttribute("user", dbuser);
	
			List<ReservationVO> latestReservation = reservationService.getLatestReservation(meNum);
			model.addAttribute("reservations", latestReservation);
			model.addAttribute("logIn", false); // 로그인 상태
		}


		// 비로그인 상태면 호텔 예약이 없다는 "HTML fragment"만 보여주기
				model.addAttribute("logIn", false);
				return "member/reservation-hotel"; // → 로그인 필요 메시지 포함된 fragment
	}

	@GetMapping("/reservation-rent")
	public String myRent() {
		return "member/reservation-rent";
	}

	@GetMapping("/modify")
	public String modify(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
			
			MemberVO member = null;

			// 1. 일반 로그인 사용자인지 체크
			if (user != null) {
					member = user.getUser();
			}
			// 2. 소셜 로그인 사용자인지 체크
			else if (oauth2user != null) {
					// 소셜 로그인 정보에서 이메일과 provider 가져오기
					String email = (String) oauth2user.getAttributes().get("email");
					String provider = ((String) oauth2user.getAttributes()
															.getOrDefault("provider", "NORMAL")).toUpperCase();

					// 이메일과 provider로 회원 정보 조회
					member = memberService.getMemberByEmailAndProvider(email, provider);
			}
			// 3. 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
			else {
					return "redirect:/member/signIn";
			}

			// 4. 회원 정보 모델에 저장 (JSP에서 ${user}로 접근 가능)
			model.addAttribute("user", member);

			// 5. 생년월일(yyyymmdd) 파싱해서 년, 월, 일 따로 모델에 저장
			String birthday = member.getMe_birthday();
			if (birthday != null && birthday.length() == 8) {
					String year = birthday.substring(0, 4);
					String month = birthday.substring(4, 6);
					String day = birthday.substring(6, 8);

					model.addAttribute("year", year);
					// 월, 일은 앞자리 0 제거해서 숫자로 저장
					model.addAttribute("month", Integer.parseInt(month));
					model.addAttribute("day", Integer.parseInt(day));
			}

			// 6. 수정 폼 뷰 이름 반환
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
	public String updateUser(Model model, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
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

		member.setMe_num(member.getMe_num());

		boolean updateUser = memberService.updateUser(member);

		if(updateUser) {
        model.addAttribute("msg", "회원 정보가 성공적으로 수정되었습니다.");
    } else {
        model.addAttribute("msg", "회원 정보 수정에 실패했습니다.");
    }
    model.addAttribute("url", "/member/mypage");
		
		return "msg";
	}
	
}
