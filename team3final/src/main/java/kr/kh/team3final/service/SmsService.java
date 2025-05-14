package kr.kh.team3final.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import kr.kh.team3final.dao.LodgingDAO;
import kr.kh.team3final.model.vo.LodgingVO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
