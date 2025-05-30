package kr.kh.team3final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.kh.team3final.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rent")
public class RentCarController {
	
	@Autowired
	CarService carService;

	@GetMapping("/detail")
	public String rentCarDetail() {

		return "rent/detail";
	}
	
	@GetMapping("/detailSummary")
	public String detailSummary(@RequestParam String param) {
		return new String();
	}
	
}
