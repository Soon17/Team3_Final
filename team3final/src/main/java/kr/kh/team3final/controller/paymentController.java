package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class paymentController {

  @GetMapping("/payment")
	public String payment() {
		return "pay/payment";
	}
	
	@GetMapping("/summary")
	public String paymentSummary() {
		return "pay/summary";
	}

}
