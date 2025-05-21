package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {
	@Autowired
	RegionService regionService;

	@Autowired
	LodgingService lodgingService;

	@GetMapping("/")
	public String main(Model model) {
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);
		return "home";
	}

	@GetMapping("/regionLodging")
	public String getRegionLodging(Model model, @RequestParam("rg_num") int rg_num) {
		List<LodgingVO> lodgingList = lodgingService.getRegionLodgingList(rg_num);
		return "regionLodgingList";
	}

}
