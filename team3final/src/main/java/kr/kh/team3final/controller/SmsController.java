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
    public boolean sendSms(@RequestParam String me_number,@RequestParam String type, HttpSession session, Model model) {
        String code = String.valueOf((int)(Math.random() * 900000) + 100000); // 6자리 랜덤 숫자

        // try {
        //     if(smsService.sendSms(me_number, code)){
        //         return true;
        //     }
		// 	return false;			
        // } catch (Exception e) {
		// 	return false;
        // }
        System.out.println(type);
        switch (type) {
            case "id":
                session.setAttribute("smsIdCode", code);
                session.setMaxInactiveInterval(300); // 5분 유효
                return true;
        
            case "signup":
                session.setAttribute("smsCode", code);
                session.setMaxInactiveInterval(300); // 5분 유효
                return true;
            case "pw":
                session.setAttribute("smsPwCode", code);
                session.setMaxInactiveInterval(300); // 5분 유효
                return true;
        }
        
        return true;
    }
    @PostMapping("/check")
    @ResponseBody
    public Boolean checkSMS(@RequestParam String userCode,@RequestParam String type, HttpSession session) {
        String code = ""; 
        switch (type) {
            case "id":
                code = (String) session.getAttribute("smsIdCode");
                break;
        
            case "signup":
                code = (String) session.getAttribute("smsCode");
                break;
            case "pw":
                code = (String) session.getAttribute("smsPwCode");
                break;
        }
        if(code == null || !code.equals(userCode)){
            return false;
        }
        return true;
    }	

}
