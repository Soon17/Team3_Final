package kr.kh.team3final.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import kr.kh.team3final.model.dto.Rental_ReviewDTO;
import kr.kh.team3final.model.vo.CarVO;
import kr.kh.team3final.model.vo.RentalVO;
import kr.kh.team3final.model.vo.ReviewVO;
import kr.kh.team3final.service.CarService;
import kr.kh.team3final.service.RentalService;
import kr.kh.team3final.service.ReviewService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kr.kh.team3final.model.vo.ReReservationVO;
import kr.kh.team3final.service.ReReservationService;
import kr.kh.team3final.utils.CustomUser;
import kr.kh.team3final.utils.Util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/rent")
public class RentCarController {

	@Autowired
	CarService carService;

	@Autowired
	RentalService rentalService;

	@Autowired
	ReReservationService reReservationService;

	@Autowired
	ReviewService reviewService;

	@GetMapping("/detail")
	public String showCarDetailPage(
			@RequestParam("cr_id") int cr_Id,
			@RequestParam("checkTime") String checkTime,
			Model model) {

		// 날짜 파싱
		String[] times = checkTime.split("~");
		String checkin = times[0].trim().replaceAll("\\(.*?\\)", "").trim();
		String checkout = times[1].trim().replaceAll("\\(.*?\\)", "").trim();

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate in = LocalDate.parse(checkin, fmt);
		LocalDate out = LocalDate.parse(checkout, fmt);
		long rentalDays = ChronoUnit.DAYS.between(in, out);
		if (rentalDays < 1)
			rentalDays = 1;

		// car, rental 조회
		CarVO car = carService.selectCarById(cr_Id);
		RentalVO rental = rentalService.getRentalInfo(car.getCr_re_num());

		// 리뷰 평점 분포
		List<Map<String, Object>> raw = reviewService.getRatingCount(car.getCr_re_num());
		Map<String, Integer> ratingCounts = new HashMap<>();
		if (raw != null) {
			for (Map<String, Object> row : raw) {
				String rating = String.valueOf(((Number) row.get("rv_rating")).intValue());
				int count = ((Number) row.get("cnt")).intValue();
				ratingCounts.put(rating, count);
			}
		}
		// 리뷰
		List<Rental_ReviewDTO> reviewList = reviewService.rentalReviews(car.getCr_re_num());

		model.addAttribute("car", car);
		model.addAttribute("rental", rental);
		model.addAttribute("checkin", checkin);
		model.addAttribute("checkout", checkout);
		model.addAttribute("rentalDays", rentalDays);
		model.addAttribute("checkTime", checkTime);
		model.addAttribute("ratingCounts", ratingCounts);
		model.addAttribute("avg_rating", rental.getAvg_rating());
		model.addAttribute("review_count", rental.getReview_count());
		model.addAttribute("reviewList", reviewList);

		return "rent/detail";
	}

	@GetMapping("/detailSummary")
	public String detailSummary(Model model) {

		return "rent/detailSummary";
	}
	@GetMapping("/payment")
	public String rentPayment(HttpSession sesssion,
		Model model,@RequestParam String checkTime,@RequestParam int cr_id) {
		String[] parts = checkTime.split(" ~ ");
		String checkIn = Util.parseDate(parts[0]);
		String checkOut = Util.parseDate(parts[1]);
		CarVO car = carService.getCar(cr_id);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("car", car);
		sesssion.setAttribute("parts", parts);
		return "rentPay/payment";
	}

	@GetMapping("/summary")
	public String rentPaymentSummary() {
		return "rentPay/summary";
	}
	@PostMapping("/kakao/complete")
	@ResponseBody
	public Map<String, Object> insertLoReservation(Model model, HttpSession session,@RequestBody ReReservationVO rr,
			@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
				
		if (oauth2user != null) {
			rr.setRr_me_num((int) oauth2user.getAttributes().get("num"));
		} else if (user != null) {
			rr.setRr_me_num(user.getUser().getMe_num());
		}
		String[] par = (String[]) session.getAttribute("parts");
		String rr_checkIn = Util.formatDate(par[0]);
		String rr_checkOut = Util.formatDate(par[1]);
		rr.setRr_checkIn(rr_checkIn);
		rr.setRr_checkOut(rr_checkOut);
		System.out.println(rr);
		reReservationService.insertReReservation(rr);
		Map<String, Object> map = new HashMap<>();
		map.put("ok", true);
		return map;
	}
}
