package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/searching")
public class searchController {

  @GetMapping("/search")
	public String search() {
		return "searching/search";
	}
	
	@GetMapping("/find")
	public String find() {
		return "searching/find";
	}

}
