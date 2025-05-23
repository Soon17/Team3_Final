package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class PaymentController {

  @GetMapping("/payment")
	public String payment(HttpSession session) {
		session.setAttribute("previousPage", "/pay/payment");
		System.out.println("결제 페이지 : " + session.getAttribute("previousPage"));
		return "pay/payment";
	}
	
	@GetMapping("/summary")
	public String paymentSummary() {
		return "pay/summary";
	}

}
