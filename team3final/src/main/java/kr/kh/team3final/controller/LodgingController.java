package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.model.vo.ReviewVO;
import kr.kh.team3final.model.vo.RoomVO;
import kr.kh.team3final.model.vo.ThumbnailVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.RegionService;
import kr.kh.team3final.service.ReviewService;
import kr.kh.team3final.service.RoomService;
import kr.kh.team3final.service.ThumbnailService;

@Controller
public class LodgingController {

  @Autowired
  LodgingService lodgingService;

  @Autowired
  RegionService regionService;

  @Autowired
  RoomService roomService;

  @Autowired
  ThumbnailService thumbnailService;

  @Autowired
  ReviewService reviewService;

  @GetMapping("/reserv")
  public String showReservPage(
      Model model,
      @RequestParam("checkTime") String checkTime,
      @RequestParam("rm_person") String rm_person,
      @RequestParam("ld_num") int ld_num) {

    List<RegionVO> list = regionService.getRegionList();
    LodgingVO lodging = lodgingService.allLodgingList(ld_num);
    List<RoomVO> roomList = roomService.selectRoomListByLodging(ld_num);
    List<ThumbnailVO> thumbList = thumbnailService.selectThumbnail("lodging", ld_num);
    List<ReviewVO> reviewList = reviewService.getReviewList("lodging", ld_num);

    for (RoomVO room : roomList) {
      List<ThumbnailVO> roomThumbList = thumbnailService.selectThumbnail("room", room.getRm_num());
      room.setThumbList(roomThumbList);
    }

    model.addAttribute("regionList", list);
    model.addAttribute("checkTime", checkTime);
    model.addAttribute("rm_person", rm_person);
    model.addAttribute("lodging", lodging);
    model.addAttribute("roomList", roomList);
    model.addAttribute("thumbList", thumbList);

    return "reserv";
  }

}