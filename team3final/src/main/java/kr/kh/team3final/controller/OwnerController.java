package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.dto.LodgingDTO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	LodgingService lodgingService;

	@Autowired
	RegionService regionService;


	@GetMapping("/uploadLodging")
	public String uploadLodging(Model model) {
		List<String> defaultOptions = lodgingService.getDefaultOptions();
		List<RegionVO> regions = regionService.getRegionList();
		model.addAttribute("defaultOptions", defaultOptions);
		model.addAttribute("regions", regions);
		return "owner/uploadLodging";
	}

	@PostMapping("/uploadLodgingPost")
	public String uploadLodgingPost(Model model, LodgingDTO lodgingDTO, @RequestParam("options") List<String> selectedOptions) {
		model.addAttribute("msg", "등록되었습니다. 숙소 정보: " + lodgingDTO);	
		model.addAttribute("url", "/");
		return "msg";
	}

}
