package kr.kh.team3final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @GetMapping("/msg")
    public String showMessagePage(
        @RequestParam(required = false) String msg,
        @RequestParam(required = false) String url,
        Model model
    ) {
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "msg";
    }
}