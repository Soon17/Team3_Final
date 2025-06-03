package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class RentalVO {
	int re_num;
	String re_name;
	String re_number;
	String re_region;
	int re_menum;
	int re_rgnum;

	Double avg_rating;
	int review_count;
}