package kr.kh.team3final.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RentReservationVO {

private int rr_num; //렌트예약번호
private Date rr_checkIn; //대여시간
private Date rr_chekout;  //반납시간
private int rr_price;  //렌트가격
private Date rr_date; //대여일자
private String rr_state; //예약상태(예약취소, 완료, 환불)
private String rr_name; //예약자이름
private String rr_number; //예약자 전화번호
private int rr_birth; //예약자 생년월일
private int rr_cp_num; //예약 차량 패키지 번호
private int rr_me_num; //예약자 유저넘버
private String ct_name; //차량 이름
private String re_name;   // 업체명

}
