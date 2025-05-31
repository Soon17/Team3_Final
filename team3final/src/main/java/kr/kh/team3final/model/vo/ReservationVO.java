package kr.kh.team3final.model.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReservationVO {
	int lr_num; //예약번호
	Date lr_checkin; //입실
	Date lr_checkout; //퇴실
	int lr_count; //예약인원
	int lr_total_price; //예약금액
	String lr_state; //예약상태
	Date lr_date; //예약일자
	String lr_booker_name; //예약자 이름
	String lr_guest_name; //숙박자 이름
	String lr_guest_number; //숙박자 번호
	String lr_content; //요청사항
	String lr_meal_state; //조식여부
	int lr_me_num; //회원번호
	int lr_rm_num; //객실번호
	List<ReservationVO> reservationList; //예약 내역 리스트
	String lr_me_name; //회원 이름
	String lr_rm_name; //객실 이름
	String lr_ld_name; //숙소 이름
}
