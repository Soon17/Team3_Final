package kr.kh.team3final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import kr.kh.team3final.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rent")
public class RentCarController {
	
	@Autowired
	CarService carService;

	@GetMapping("/detail")
	public String rentCarDetail(Model model) {

		return "rent/detail";
	}
	
	@GetMapping("/detailSummary")
	public String detailSummary(Model model) {
		
		return "rent/detailSummary";
	}
	
}
