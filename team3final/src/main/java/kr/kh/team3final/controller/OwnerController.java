package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.service.LodgingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	LodgingService lodgingService;

	@GetMapping("/uploadLodging")
	public String uploadLodging(Model model) {
		List<String> defaultOptions = lodgingService.getDefaultOptions();
		model.addAttribute("defaultOptions", defaultOptions);
		return "owner/uploadLodging";
	}

	@PostMapping("/uploadLodgingPost")
	public String uploadLodgingPost(Model model, @RequestParam("options") List<String> selectedOptions) {
		model.addAttribute("msg", "등록되었습니다. 선택 옵셥: " + selectedOptions);	
		model.addAttribute("url", "/");
		return "msg";
	}

}
