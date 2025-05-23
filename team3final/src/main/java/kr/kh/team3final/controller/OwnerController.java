package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/owner")
public class OwnerController {
	@GetMapping("/uploadLodging")
	public String uploadLodging() {
		return "owner/uploadLodging";
	}
	
}
