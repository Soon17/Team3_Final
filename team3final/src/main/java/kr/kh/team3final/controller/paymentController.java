package kr.kh.team3final.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.kh.team3final.model.vo.ChoiceOptionVO;
import kr.kh.team3final.model.vo.LoResrvationVO;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.RoomVO;
import kr.kh.team3final.service.ChoiceOptionService;
import kr.kh.team3final.service.LoResrvationService;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RoomService;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/pay")
public class PaymentController {
	
	@Value("${kakao.pay-store}")
    private String store;

	@Value("${kakao.pay-channel}")
    private String channel;

	@Autowired
	LodgingService lodgingService;

	@Autowired
	ChoiceOptionService choiceOptionService;

	@Autowired
	RoomService roomService;

	@Autowired
	LoResrvationService loResrvationService;

	@GetMapping("/payment") 
	public String payment(HttpSession sesssion,
		Model model,@RequestParam int ld_num,@RequestParam String checkTime,@RequestParam String rm_person,@RequestParam int rm_num) {
		LodgingVO lodging=  lodgingService.getLodging(ld_num);
		RoomVO room = roomService.getRoom(rm_num);
		List<ChoiceOptionVO> choiceOptions = choiceOptionService.getChoiceOpotionList(ld_num);
		String[] parts = checkTime.split(" ~ ");
		String checkIn = parseDate(parts[0]);
		String checkOut = parseDate(parts[1]);

		sesssion.setAttribute("checkIn", checkIn);
		sesssion.setAttribute("checkOut", checkOut);
		sesssion.setAttribute("rm_person", rm_person);
		sesssion.setAttribute("rm_num", rm_num);
		model.addAttribute("lodging", lodging);
		model.addAttribute("room", room);
		model.addAttribute("choiceOptions", choiceOptions);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("rm_person", rm_person);
		return "pay/payment";
	}

	@GetMapping("/summary")
	public String paymentSummary(Model model,@RequestParam String rm_name) {
		model.addAttribute("rm_name", rm_name);
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
	@PostMapping("/kakao/apiKey")
	@ResponseBody
	public Map<String, Object> getApi(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channel", channel);
		map.put("store", store);
		return map;
	}
	@PostMapping("/kakao/complete")
	public String insertLoReservation(LoResrvationVO loResrvationVO) {
		
		
		return null;
	}
	
}
