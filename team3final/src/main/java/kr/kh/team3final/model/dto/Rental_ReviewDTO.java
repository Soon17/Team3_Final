package kr.kh.team3final.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental_ReviewDTO {
	int rv_num;
	int rv_rating;
	Date rv_date;
	String rv_content;
	Date rvDate;

	int re_num;
	String re_name;
	String re_region;
	String me_nick;
	String ct_name;
	int rr_num;
}
