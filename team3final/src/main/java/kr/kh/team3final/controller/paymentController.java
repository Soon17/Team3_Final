package kr.kh.team3final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.service.LodgingService;

@Controller
@RequestMapping("/pay")
public class PaymentController {

	@Autowired
	LodgingService lodgingService;

	@GetMapping("/payment")
	public String payment(Model model,@RequestParam int ld_num,@RequestParam String checkTime,@RequestParam String rm_person) {
		LodgingVO lodging=  lodgingService.getLodging(ld_num);
		String[] parts = checkTime.split(" ~ ");
		String checkIn = parseDate(parts[0]);
		String checkOut = parseDate(parts[1]);
		model.addAttribute("lodging", lodging);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("rm_person", rm_person);
		return "pay/payment";
	}

	@GetMapping("/summary")
	public String paymentSummary() {
		return "pay/summary";
	}
    private static String parseDate(String checkTime) {
        // 예: "2025.06.02(월)"
        String datePart = checkTime.substring(5, 10); // "06.02"
        String[] dateSplit = datePart.split("\\.");
        String month = String.valueOf(Integer.parseInt(dateSplit[0])); // 앞자리 0 제거
        String day = dateSplit[1];
        String dayOfWeek = checkTime.substring(checkTime.indexOf('(') + 1, checkTime.indexOf(')'));

        return month + "/" + day + "/" + dayOfWeek;
    }
}
