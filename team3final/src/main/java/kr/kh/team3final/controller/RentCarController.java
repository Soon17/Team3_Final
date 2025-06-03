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

import kr.kh.team3final.model.vo.CarVO;
import kr.kh.team3final.model.vo.RentalVO;
import kr.kh.team3final.model.vo.ReviewVO;
import kr.kh.team3final.service.CarService;
import kr.kh.team3final.service.RentalService;
import kr.kh.team3final.service.ReviewService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rent")
public class RentCarController {

	@Autowired
	CarService carService;

	@Autowired
	RentalService rentalService;

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
		List<ReviewVO> reviewList = reviewService.rentalReviews(car.getCr_re_num());

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

}
