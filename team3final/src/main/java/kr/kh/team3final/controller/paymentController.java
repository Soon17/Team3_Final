package kr.kh.team3final.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import kr.kh.team3final.utils.CustomUser;

@Controller
@RequestMapping("/pay")
public class PaymentController {

	@Value("${kakao.pay-store}")
	private String store;

	@Value("${kakao.pay-channel}")
	private String kakaoChannel;

	@Value("${toss.pay-channel}")
	private String tossChannel;
	
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
			Model model, @RequestParam int ld_num, @RequestParam String checkTime, @RequestParam String rm_person,
			@RequestParam int rm_num) {
		LodgingVO lodging = lodgingService.getLodging(ld_num);
		RoomVO room = roomService.getRoom(rm_num);
		List<ChoiceOptionVO> choiceOptions = choiceOptionService.getChoiceOpotionList(ld_num);
		String[] parts = checkTime.split(" ~ ");
		String checkIn = parseDate(parts[0]);
		String checkOut = parseDate(parts[1]);
		sesssion.setAttribute("ld_num", ld_num);
		sesssion.setAttribute("parts", parts);
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
	public String paymentSummary(Model model, @RequestParam String rm_name) {
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
	public Map<String, Object> getApi(@RequestParam String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		switch (type) {
			case "kakao":
				map.put("channel", kakaoChannel);
				break;

			case "toss":
				map.put("channel", tossChannel);
				break;
		}

		map.put("store", store);
		return map;
	}

	@PostMapping("/kakao/complete")
	@ResponseBody
	public Map<String, Object> insertLoReservation(Model model, HttpSession session, @RequestBody LoResrvationVO lr,
			@AuthenticationPrincipal CustomUser user, @AuthenticationPrincipal OAuth2User oauth2user) {
		if (oauth2user != null) {
			lr.setLr_me_num((int) oauth2user.getAttributes().get("num"));
		} else if (user != null) {
			lr.setLr_me_num(user.getUser().getMe_num());
		}
		String[] par = (String[]) session.getAttribute("parts");
		int rm_num = (int) session.getAttribute("rm_num");
		String lr_checkIn = formatDate(par[0]);
		String lr_checkOut = formatDate(par[1]);
		lr.setLr_checkIn(lr_checkIn);
		lr.setLr_checkOut(lr_checkOut);
		lr.setLr_rm_num(rm_num);
		loResrvationService.insertLoReservation(lr);
		Map<String, Object> map = new HashMap<>();
		map.put("ok", true);
		return map;
	}

	public static String formatDate(String dateStr) {
		// 괄호 앞까지 자르고 점(.)을 하이픈(-)으로 교체
		String dateOnly = dateStr.split("\\(")[0];
		return dateOnly.replace(".", "-");
	}
}
