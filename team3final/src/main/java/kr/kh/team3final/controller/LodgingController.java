package kr.kh.team3final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.service.LodgingService;

@Controller
public class LodgingController {

  @Autowired
  LodgingService lodgingService;

  @GetMapping("/allLodging")
  public String getRegionLodging(Model model, @RequestParam("ld_num") int ld_num) {
    List<LodgingVO> lodgingAll = lodgingService.allLodgingList(ld_num);
    model.addAttribute("lodgingAll", lodgingAll);
    return "allLodgingList";
  }
}