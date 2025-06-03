package kr.kh.team3final.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.team3final.dao.MemberDAO;
import kr.kh.team3final.model.dto.LodgingDTO;
import kr.kh.team3final.model.dto.RoomDTO;
import kr.kh.team3final.model.vo.DefaultOptionVO;
import kr.kh.team3final.model.vo.MemberVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.ChoiceOptionService;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;
import kr.kh.team3final.service.RoomService;
import kr.kh.team3final.service.ThumbnailService;
import kr.kh.team3final.utils.CustomUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	MemberDAO memberDAO;
	@Autowired
	LodgingService lodgingService;
	@Autowired
	RegionService regionService;
	@Autowired
	RoomService roomService;
	@Autowired
	ThumbnailService thumbnailService;
	@Autowired
	ChoiceOptionService choiceOptionService;

	@GetMapping("/uploadLodging")
	public String uploadLodging(Model model) {
		List<String> defaultOptions = lodgingService.getDefaultOptions();
		List<RegionVO> regions = regionService.getRegionList();
		model.addAttribute("defaultOptions", defaultOptions);
		model.addAttribute("regions", regions);
		return "owner/uploadLodging";
	}

	@PostMapping("/uploadLodgingPost")
	public String uploadLodgingPost(
			Model model,
			LodgingDTO lodgingDTO,
			@AuthenticationPrincipal CustomUser customUser, @AuthenticationPrincipal OAuth2User oauth2User,
			@RequestParam(value = "options", required = false) List<String> selectedOptions,
			@RequestParam(value = "added-options", required = false) String addedOptions,
			@RequestParam(value = "loadging-thumbnail", required = false) List<MultipartFile> LDTH,
			@RequestParam(value = "room-thumbnail", required = false) List<MultipartFile> RMTH) throws IOException {

		MemberVO user = null;
		if (customUser != null) {
			user = customUser.getUser();
		} else if (oauth2User != null) {
			user = memberDAO.selectMember(oauth2User.getName());
		}
		
		// 해당 숙소에 ld_me_num 지정
		lodgingDTO.setLd_me_num(user.getMe_num());
		// 숙소 정보 업로드
		boolean uploadLD = lodgingService.uploadLodging(lodgingDTO); // 구현 완료

		// 기본 옵션 리스트 객체화
		if(selectedOptions != null && !selectedOptions.isEmpty()){
			for(String choiceOption : selectedOptions){
				DefaultOptionVO defaultOptionVO = lodgingService.getDo_num(choiceOption);
				lodgingService.uploadDefaultOption(defaultOptionVO);	// DO에 추가
				choiceOptionService.uploadChoiceOption(defaultOptionVO.getDo_num(), lodgingDTO.getLd_num());
			}
		}

		// 추가 옵션 문자열 리스트화
		List<String> result = Arrays.stream(addedOptions.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())  // 빈 값 제거
                            .collect(Collectors.toList());
		// 추가 옵션 리스트 객체화
		if(result != null && !result.isEmpty()){
			for(String addedOption : result){
				DefaultOptionVO defaultOptionVO = new DefaultOptionVO();
				defaultOptionVO.setDo_name(addedOption);
				lodgingService.uploadDefaultOption(defaultOptionVO);	// DO에 추가
				choiceOptionService.uploadChoiceOption(defaultOptionVO.getDo_num(), lodgingDTO.getLd_num());
			}
		}

		// 해당 숙소의 룸들에 rm_ld_num지정
		// for (RoomDTO room : lodgingDTO.getRooms()) {
		// room.setRm_ld_num(lodgingDTO.getLd_num());
		// }
		// 룸 정보 업로드
		// boolean uploadRM = roomService.uploadRooms(lodgingDTO.getRooms()); // 구현
		// 완료

		// boolean uploadLDTH = thumbnailService.uploadLDTH(LDTH);
		// boolean uploadRMTH = thumbnailService.uploadRMTH(RMTH);


		/* 서버에 썸네일 업로드 */
		// String uploadDir = new File("").getAbsolutePath() +
		// "/team3final/src/main/resources/static/img";

		// for (MultipartFile file : LDTH) {
		// if (!file.isEmpty()) {
		// String originalName = file.getOriginalFilename();
		// String uuid = UUID.randomUUID().toString();
		// String uniqueName = uuid + "_" + originalName;

		// File destination = new File(uploadDir, uniqueName);
		// file.transferTo(destination);
		// System.out.println("업로드 성공: " + uniqueName);
		// }
		// }

		model.addAttribute("msg", "등록되었습니다.");
		model.addAttribute("url", "/");
		return "msg";
	}

}
