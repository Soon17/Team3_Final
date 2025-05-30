package kr.kh.team3final.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.team3final.model.dto.LodgingDTO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;
import kr.kh.team3final.service.RoomService;
import kr.kh.team3final.service.ThumbnailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	LodgingService lodgingService;
	@Autowired
	RegionService regionService;
	@Autowired
	RoomService roomService;
	@Autowired
	ThumbnailService thumbnailService;

	@GetMapping("/uploadLodging")
	public String uploadLodging(Model model) {
		List<String> defaultOptions = lodgingService.getDefaultOptions();
		List<RegionVO> regions = regionService.getRegionList();
		model.addAttribute("defaultOptions", defaultOptions);
		model.addAttribute("regions", regions);
		return "owner/uploadLodging";
	}

	@PostMapping("/uploadLodgingPost")
	public String uploadLodgingPost(Model model, LodgingDTO lodgingDTO,
			@RequestParam("options") List<String> selectedOptions,
			@RequestParam("loadging-thumbnail") List<MultipartFile> LDTH) throws IOException {

		// boolean uploadLodging = lodgingService.uploadLodging(lodgingDTO);
		// boolean uploadRoom = roomService.uploadRoom(lodgingDTO.getRooms());
		// boolean uploadLDTH = thumbnailService.uploadLDTH(LDTH);
		// boolean uploadRMTH = thumbnailService.uploadRMTH(RMTH);

		String uploadDir = new File("").getAbsolutePath() + "/team3final/src/main/resources/static/img";

		for (MultipartFile file : LDTH) {
			if (!file.isEmpty()) {
				String originalName = file.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				String uniqueName = uuid + "_" + originalName;

				File destination = new File(uploadDir, uniqueName);
				file.transferTo(destination);
				System.out.println("업로드 성공: " + uniqueName);
			}
		}

		model.addAttribute("msg", "등록되었습니다.");
		model.addAttribute("url", "/");
		return "msg";
	}

}
