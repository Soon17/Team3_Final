package kr.kh.team3final.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent_ReviewDTO {
	int rr_num; //예약번호
	Date rr_date; //대여일자
	String ct_name; //차량이름
	String re_name; //업체명
	int rv_num; //리뷰번호
	int rv_rating; //별점
	Date rv_date; //작성일자
	String rv_content; //작성내용
}
