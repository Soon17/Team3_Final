package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.RegionService;
import kr.kh.team3final.model.vo.CarVO;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.SearchCriteria;
import kr.kh.team3final.service.CarService;
import kr.kh.team3final.service.LodgingService;

@Controller
@RequestMapping("/searching")
public class SearchController {

	@Autowired
	LodgingService lodgingService;

	@Autowired
	RegionService regionService;

	@Autowired
	CarService carService;

	@GetMapping("/search")
	public String search(
			Model model,
			SearchCriteria cri) {
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);
		model.addAttribute("rg_name", cri.getRg_name());
		model.addAttribute("checkTime", cri.getCheckTime());
		model.addAttribute("rm_person", cri.getRm_person());
		List<LodgingVO> lodgingList = lodgingService.getSearchlodgingList(cri);

		model.addAttribute("lodgingList", lodgingList);
		model.addAttribute("cri", cri);
		return "searching/search";
	}

	@GetMapping("/carsearch")
	public String carsearch(
			Model model,
			SearchCriteria cri) {
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);
		model.addAttribute("rg_name", cri.getRg_name());
		model.addAttribute("checkTime", cri.getCheckTime());
		List<CarVO> carList = carService.getSearchCarList(cri);

		model.addAttribute("carList", carList);
		model.addAttribute("cri", cri);
		return "searching/carsearch";
	}

	@GetMapping("/lodging")
	public String searchlodging(
			Model model,
			SearchCriteria cri) {

		List<LodgingVO> lodgingList = lodgingService.getSearchlodgingList(cri);

		model.addAttribute("lodgingList", lodgingList);
		model.addAttribute("cri", cri);

		return "searching/searchLodging";
	}

	@GetMapping("/find")
	public String find() {
		return "searching/find";
	}

}
