package kr.kh.team3final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.kh.team3final.service.SmsService;

@Controller
@RequestMapping("/sms")
public class SmsController {
	
	@Autowired
	SmsService smsService;

    @PostMapping("/send")
    @ResponseBody
    public Boolean sendSms(@RequestParam String me_number, HttpSession session, Model model) {
        String code = String.valueOf((int)(Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
        session.setAttribute("smsCode", code);
        session.setMaxInactiveInterval(300); // 5분 유효
        try {
            if(smsService.sendSms(me_number, code)){
                return true;
            }
			return false;			
        } catch (Exception e) {
			return false;
        }
    }
    @PostMapping("/check")
    @ResponseBody
    public Boolean checkSMS(@RequestParam String userCode, HttpSession session) {
        String code = (String) session.getAttribute("smsCode");
        if(code == null || !code.equals(userCode)){
            return false;
        }
        return true;
    }	

}
