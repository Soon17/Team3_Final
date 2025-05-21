package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

	@GetMapping("/detail/{eventId}")
	public String eventDetail(@PathVariable String eventId) {
			System.out.println("이벤트 ID: " + eventId);  // 콘솔에 1 출력됨
			return "event/detail" + eventId;
	}

	@GetMapping("/list")
	public String eventList() {
		return "event/list";
	}
	
}
