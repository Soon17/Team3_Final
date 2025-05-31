package kr.kh.team3final.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.model.vo.RentalVO;
import kr.kh.team3final.service.RegionService;
import kr.kh.team3final.service.RentalService;
import kr.kh.team3final.model.dto.AvailableCarDTO;
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

	@Autowired
	RentalService rentalService;

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
	public String carsearch(Model model, SearchCriteria cri) {
		// 지역 목록
		List<RegionVO> list = regionService.getRegionList();
		model.addAttribute("regionList", list);

		// 조건값 전달
		model.addAttribute("rg_name", cri.getRg_name());
		model.addAttribute("checkTime", cri.getCheckTime());
		model.addAttribute("cri", cri);

		// 차량 목록 조회
		List<CarVO> carList = carService.getSearchCarList(cri);
		model.addAttribute("carList", carList);

		return "searching/carsearch";
	}

	@GetMapping("/find")
	public String find() {
		return "searching/find";
	}

	@GetMapping("/carfind")
	public String carfind() {
		return "searching/carfind";
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

	@GetMapping("/car")
	public String searchCar(
			Model model,
			SearchCriteria cri) {

		List<CarVO> carList = carService.getSearchCarList(cri);

		model.addAttribute("carList", carList);
		model.addAttribute("cri", cri);

		return "searching/searchCar";
	}

	@GetMapping("/rentsearch")
	public String rentSearch(
			@RequestParam String rg_name,
			@RequestParam String checkTime,
			@RequestParam int ct_key,
			@RequestParam int cr_year,
			@RequestParam String cr_fuel_type,
			@RequestParam String cr_trans,
			Model model) {

		model.addAttribute("rg_name", rg_name);
		model.addAttribute("checkTime", checkTime);

		// 차량 상단 정보 (썸네일, 이름, 가격범위 등)
		Map<String, Object> carTop = carService.selectCarInfo(ct_key, cr_year, cr_fuel_type, cr_trans);
		model.addAttribute("carTop", carTop);

		// DTO 기반 렌탈 리스트
		List<AvailableCarDTO> rentalList = rentalService.getAvailableCarList(ct_key, cr_year, cr_fuel_type, cr_trans);
		model.addAttribute("rentalList", rentalList);

		List<RegionVO> regionList = regionService.getRegionList(); // 전체 지역 가져오는 메서드
		model.addAttribute("regionList", regionList);
		return "searching/rentsearch";
	}

}
