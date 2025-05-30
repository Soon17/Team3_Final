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
import kr.kh.team3final.service.ReviewService;
import kr.kh.team3final.utils.CustomUser;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	MemberDAO memberDAO;

	@Autowired
	ReviewService reviewService;

	@PostMapping("/insert")
	@ResponseBody
	public String insertReview(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user, @RequestParam("rv_rating") int rating,
			@RequestParam("rv_content") String content, @RequestParam("rv_number") int rvNumber, @RequestParam("rv_table_name") String tableName) {

		MemberVO member = null;

		if(user != null){
			member = user.getUser();
		}
		else if(oauth2user != null){
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return "fail";
		}

		ReviewVO review = new ReviewVO();
		review.setRv_rating(rating);
		review.setRv_content(content);
		review.setRv_number(rvNumber);
		review.setRv_table_name(tableName);
		review.setRv_me_num(member.getMe_num());

		int result = reviewService.insertReview(review);

		return result > 0 ? "success" : "fail";
	}

	@GetMapping("/check")
	@ResponseBody
	public ReviewVO checkReview(@RequestParam("rv_number") int rvNumber, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		MemberVO member = null;
		if(user != null){
			member = user.getUser();
		} else if(oauth2user != null){
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return null;
		}
		return reviewService.detailReview(rvNumber, member.getMe_num());
	}

	@GetMapping("/detail")
	@ResponseBody
	public ReviewVO getReview(@RequestParam("rv_number") int rvNumber, @RequestParam("rv_table_name") String tableName, @AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		MemberVO member = null;
		if(user != null){
			member = user.getUser();
		} else if(oauth2user != null){
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return null;
		}
		return reviewService.detailReview(rvNumber, member.getMe_num(),tableName);
	}

	@PostMapping("/update")
	@ResponseBody
	public String updateReview(@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user, @RequestParam("rv_rating") int rating,
							   @RequestParam("rv_content") String content, @RequestParam("rv_number") int rvNumber) {
		MemberVO member = null;
		if(user != null){
			member = user.getUser();
		} else if(oauth2user != null){
			member = memberDAO.selectMember(oauth2user.getName());
		} else {
			return "fail";
		}

		ReviewVO review = new ReviewVO();
		review.setRv_rating(rating);
		review.setRv_content(content);
		review.setRv_number(rvNumber);
		review.setRv_me_num(member.getMe_num());
		review.setRv_table_name("room");

		int result = reviewService.updateReview(review);

		return result > 0 ? "success" : "fail";
	}

	@PostMapping("/delete")
@ResponseBody
public String deleteReview(@RequestParam("rv_number") int rvNumber, 
                           @AuthenticationPrincipal CustomUser user, 
                           @AuthenticationPrincipal OAuth2User oauth2user) {
    System.out.println("deleteReview 호출됨, rvNumber = " + rvNumber);
    MemberVO member = null;
    if(user != null){
        member = user.getUser();
        System.out.println("CustomUser 인증됨, member me_num = " + member.getMe_num());
    } else if(oauth2user != null){
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
