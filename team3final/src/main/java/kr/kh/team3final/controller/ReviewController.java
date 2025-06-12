package kr.kh.team3final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.team3final.dao.MemberDAO;
import kr.kh.team3final.model.vo.MemberVO;
import kr.kh.team3final.model.vo.ReviewVO;
import kr.kh.team3final.service.ReservationService;
import kr.kh.team3final.service.ReviewService;
import kr.kh.team3final.utils.CustomUser;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	MemberDAO memberDAO;

	@Autowired
	ReviewService reviewService;

	@Autowired
	ReservationService reservationService;

	@PostMapping("/insert")
	@ResponseBody
	public String insertReview(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user,
			@RequestParam("rv_rating") int rating,
			@RequestParam("rv_content") String content, // @RequestParam("rv_number") int rvNumber,
			@RequestParam("rv_table_name") String tableName,
			@RequestParam("all_num") int allNum) {

		MemberVO member = null;
		int result=0;
		if (user != null) {
			member = user.getUser();
		} else if (oauth2user != null) {
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return "fail";
		}

		// 유효성 검사 추가
		if (rating < 1 || rating > 5) {
			return "invalid_rating";
		}

		if (content == null || content.trim().isEmpty()) {
			return "invalid_content";
		}
		
		ReviewVO review = new ReviewVO();

		if(tableName.equals("room")){
			review.setRv_number(reservationService.getRmNum(allNum));
			
		}
		if(tableName.equals("rental")){
			review.setRv_number(reservationService.getReNum(allNum));
		}
		review.setRv_rating(rating);
		review.setRv_content(content);
		review.setRv_table_name(tableName);
		review.setRv_me_num(member.getMe_num());

		result = reviewService.insertReview(review);
		return result > 0 ? "success" : "fail";		
	}

	@GetMapping("/check")
	@ResponseBody
	public ReviewVO checkReview(@RequestParam("rv_number") int rvNumber, @RequestParam("rv_table_name") String tableName,
			@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		MemberVO member = null;
		if (user != null) {
			member = user.getUser();
		} else if (oauth2user != null) {
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return null;
		}
		return reviewService.detailReview(rvNumber, member.getMe_num(), tableName);
	}

	@GetMapping("/detail")
	@ResponseBody
	public ReviewVO getReview(@RequestParam("all_num") int allNum, @RequestParam("rv_table_name") String tableName,
			@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		MemberVO member = null;
		if (user != null) {
			member = user.getUser();
		} else if (oauth2user != null) {
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return null;
		}
		if(tableName.equals("room")){
			int lrNum = allNum;
			return reviewService.detailReview(reservationService.getRmNum(lrNum), member.getMe_num(), tableName);
		}
		if(tableName.equals("rental")){
			int rrNum = allNum;
			return reviewService.detailReview(reservationService.getReNum(rrNum), member.getMe_num(), tableName);
		}
		return null;
	}

	@PostMapping("/update")
	@ResponseBody
	public String updateReview(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user,
			@RequestParam("rv_rating") int rating,
			@RequestParam("rv_content") String content, @RequestParam("all_num") int allNum,
			@RequestParam("rv_table_name") String tableName) {

		MemberVO member = null;
		if (user != null) {
			member = user.getUser();
		} else if (oauth2user != null) {
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return "fail";
		}

		// 유효성 검사
		if (rating < 1 || rating > 5) {
			return "invalid_rating";
		}
		if (content == null || content.trim().isEmpty()) {
			return "invalid_content";
		}
		
		ReviewVO review = new ReviewVO();
		if(tableName.equals("room")){
			review.setRv_number(reservationService.getRmNum(allNum));
		}
		if(tableName.equals("rental")){
			review.setRv_number(reservationService.getReNum(allNum));
		}
		review.setRv_rating(rating);
		review.setRv_content(content);
		review.setRv_me_num(member.getMe_num());
		review.setRv_table_name(tableName);

		int result = reviewService.updateReview(review);
		return result > 0 ? "success" : "fail";
	}

	@PostMapping("/delete")
	@ResponseBody
	public String deleteReview(@RequestParam("all_num") int allNum,
			@RequestParam("rv_table_name") String tableName,
			@AuthenticationPrincipal CustomUser user,
			@AuthenticationPrincipal OAuth2User oauth2user) {
		int rvNumber = 0;
		if(tableName.equals("room")){
			rvNumber = reservationService.getRmNum(allNum);
		}
		if(tableName.equals("rental")){
			rvNumber = reservationService.getReNum(allNum);
		}

		if(rvNumber == 0) return "fail";

		System.out.println("deleteReview 호출됨, rvNumber = " + rvNumber);
		MemberVO member = null;
		if (user != null) {
			member = user.getUser();
			System.out.println("CustomUser 인증됨, member me_num = " + member.getMe_num());
		} else if (oauth2user != null) {
			member = memberDAO.selectMember(oauth2user.getName());
			System.out.println("OAuth2User 인증됨, member me_num = " + member.getMe_num());
		} else {
			System.out.println("인증 실패: user, oauth2user 둘 다 null");
			return "fail";
		}


		int result = reviewService.deleteReview(rvNumber, member.getMe_num());
		System.out.println("삭제 처리 결과 result = " + result);

		return result > 0 ? "success" : "fail";
	}

}
