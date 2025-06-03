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
	String cr_thumbnail;
	
	String re_name;
	int min_price;
	int ct_key;
	String ct_name;
	String ct_type;
	int reservation_count;
}
