package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;

@Controller
@RequestMapping("/searching")
public class SearchController {

	@Autowired
	LodgingService lodgingService;

	@Autowired
	RegionService regionService;
  	@GetMapping("/search")
	public String search(
		Model model,
		@RequestParam("rg_name") String rg_name,@RequestParam("checkTime") String checkTime,@RequestParam("rm_person") String rm_person) {
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);
		model.addAttribute("rg_name", rg_name);
		model.addAttribute("checkTime", checkTime);
		model.addAttribute("rm_person", rm_person);
		List<LodgingVO> lodgingList = lodgingService.getSearchlodgingList(rg_name,checkTime,rm_person);
		System.out.println(lodgingList.size());
		return "searching/search";
	}
	
	@GetMapping("/find")
	public String find() {
		return "searching/find";
	}

}
