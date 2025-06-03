package kr.kh.team3final.controller;

import java.io.File;
import java.io.IOException;
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
import kr.kh.team3final.model.vo.DefaultOptionVO;
import kr.kh.team3final.model.vo.MemberVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.OptionService;
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
	OptionService optionService;

	@GetMapping("/uploadLodging")
	public String uploadLodging(Model model) {
		List<String> defaultOptions = optionService.getDefaultOptions();
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
			@RequestParam(value = "added-options", required = false) String addedOptions
			) throws IOException {

		MemberVO user = null;
		if (customUser != null) {
			user = customUser.getUser();
		} else if (oauth2User != null) {
			user = memberDAO.selectMember(oauth2User.getName());
		} else {
			model.addAttribute("msg", "로그인 후 이용하세요.");
			model.addAttribute("url", "/member/signIn");
			return "msg";
		}
		
		lodgingDTO.setLd_me_num(user.getMe_num());

		// 숙소 정보 업로드
		boolean uploadLD = lodgingService.uploadLodging(lodgingDTO);
		System.out.println("숙소 업로드 성공");

		// 숙소 썸네일 업로드
		String uploadDir = new File("").getAbsolutePath() +
		"/team3final/src/main/resources/static/img";
		boolean uploadLDTH = false;
		for (MultipartFile file : lodgingDTO.getLodgingThumbnailList()) {
			if (!file.isEmpty()) {
				String originalName = file.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				String uniqueName = uuid + "_" + originalName;
				
				File destination = new File(uploadDir, uniqueName);
				file.transferTo(destination);
				System.out.println("숙소 썸네일 파일: " + uniqueName);
				uploadLDTH = thumbnailService.uploadLDTH(lodgingDTO.getLd_num(), uniqueName);
			}
		}
		System.out.println("숙소 썸네일 업로드 성공");

		// 룸 정보 업로드
		boolean uploadRM = roomService.uploadRooms(lodgingDTO.getRooms(), lodgingDTO.getLd_num());
		System.out.println("룸 업로드 성공");

		// 선택 옵션 업로드
		if(selectedOptions != null && !selectedOptions.isEmpty()){
			for(String choiceOption : selectedOptions){
				int do_num = lodgingService.getDo_num(choiceOption);
				optionService.uploadChoiceOption(do_num, lodgingDTO.getLd_num());
			}
		}
		System.out.println("선택 옵션 업로드 성공");

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
				// 추가 옵션 -> 기본 옵션 업로드
				optionService.uploadDefaultOption(defaultOptionVO);
				// 추가 옵션 -> 선택 옵션 업로드
				optionService.uploadChoiceOption(defaultOptionVO.getDo_num(), lodgingDTO.getLd_num());
			}
		}
		System.out.println("추가 옵션 업로드 성공");

		model.addAttribute("msg", "등록되었습니다.");
		model.addAttribute("url", "/");
		return "msg";
	}

}
