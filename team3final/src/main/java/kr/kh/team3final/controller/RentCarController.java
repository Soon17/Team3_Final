package kr.kh.team3final.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import kr.kh.team3final.dao.ReReservationDAO;
import kr.kh.team3final.model.vo.CarVO;
import kr.kh.team3final.model.vo.LoResrvationVO;
import kr.kh.team3final.model.vo.ReReservationVO;
import kr.kh.team3final.service.CarService;
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
	ReReservationService reReservationService;

	@GetMapping("/detail")
	public String rentCarDetail() {

		return "/";
	}
	
	@GetMapping("/detailSummary")
	public String detailSummary(@RequestParam String param) {
		return new String();
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
