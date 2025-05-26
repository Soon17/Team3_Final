package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class CarVO {
	int cr_id;
	int cr_year;
	String cr_fuel_type;
	String cr_trans;
	int cr_price;
	int cr_count;
	int cr_re_num;
	Double avg_rating;
	int min_price;
	int review_count;

	private CarTypeVO carType;
}
