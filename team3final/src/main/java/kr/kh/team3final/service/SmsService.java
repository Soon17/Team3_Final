package kr.kh.team3final.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;


@Service
public class SmsService {

    @Value("${solapi.apiKey}")
    private String apiKey;

    @Value("${solapi.apiSecret}")
    private String apiSecret;

    @Value("${solapi.sender}")
    private String sender;

    public void sendSms(String phone, String code) throws IOException {
        DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.solapi.com");
        // Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요
        Message message = new Message();
        message.setFrom(sender);
        message.setTo(phone);
        message.setText("[호텔고? 렌트고?] 인증번호 [" + code + "] 를 입력해주세요 :)");

        try {
        // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
        messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
        // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
        System.out.println(exception.getFailedMessageList());
        System.out.println(exception.getMessage());
        } catch (Exception exception) {
        System.out.println(exception.getMessage());
        }
    }
}
