package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.model.vo.RoomVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;
import kr.kh.team3final.service.RoomService;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired
	RegionService regionService;

	@Autowired
	LodgingService lodgingService;

	@GetMapping("/")
	public String main(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("현재 사용자 권한 목록:");
		auth.getAuthorities().forEach(a -> System.out.println(a.getAuthority()));
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);
		return "home";
	}

	@GetMapping("/regionLodging")
	public String getRegionLodging(Model model, @RequestParam("rg_num") int rg_num, @RequestParam("type") String type) {
		List<LodgingVO> lodgingList = lodgingService.getRegionLodgingList(rg_num, type);
		model.addAttribute("lodgingList", lodgingList);
		return "regionLodgingList";
	}

	@GetMapping("/rent")
	public String rent(Model model) {
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);
		return "rent";
	}

	@GetMapping("/regionRentCar")
	public String getRegionCar(Model model, @RequestParam("rg_num") int rg_num, @RequestParam("type") String type) {
		
		return "regionCarList";
	}
}
