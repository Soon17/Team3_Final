package kr.kh.team3final.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.RegionVO;
import kr.kh.team3final.model.vo.RoomVO;
import kr.kh.team3final.model.vo.ThumbnailVO;
import kr.kh.team3final.service.LodgingService;
import kr.kh.team3final.service.MemberService;
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

  @Autowired
  MemberService memberService;

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
    List<Lodging_ReviewDTO> reviewList = reviewService.selectReview("room", ld_num);
    LodgingVO stats = reviewService.selectLodgingReviewStats(ld_num);
    List<Map<String, Object>> raw = reviewService.selectRatingCounts(ld_num);

    for (RoomVO room : roomList) {
      List<ThumbnailVO> roomThumbList = thumbnailService.selectThumbnail("room", room.getRm_num());
      room.setThumbList(roomThumbList);
    }

    Map<Integer, Integer> ratingCounts = new HashMap<>();
    for (Map<String, Object> row : raw) {
      Integer score = ((Number) row.get("rv_rating")).intValue();
      Integer cnt = ((Number) row.get("cnt")).intValue();

      ratingCounts.put(score, cnt);
    }

    model.addAttribute("regionList", list);
    model.addAttribute("checkTime", checkTime);
    model.addAttribute("rm_person", rm_person);
    model.addAttribute("lodging", lodging);
    model.addAttribute("roomList", roomList);
    model.addAttribute("thumbList", thumbList);
    model.addAttribute("reviewList", reviewList);
    model.addAttribute("avg_rating", stats.getAvg_rating());
    model.addAttribute("review_count", stats.getReview_count());
    model.addAttribute("ratingCounts", ratingCounts);

    return "reserv";
  }

}