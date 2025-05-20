package kr.kh.team3final.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	int rv_num;
	int rv_rating;
	String rv_content;
	Date rv_date;
	String rv_table_name;
	int rv_number;
	int rv_me_num;
}
