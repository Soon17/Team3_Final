package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class LoResrvationVO {
	int lr_num;
	String lr_checkIn;
	String lr_checkOut;
	int lr_count;
	int lr_total_price;
	String lr_booker_name;
	String lr_guest_name;
	String lr_guest_number;
	String lr_meal_state;
	int lr_me_num;
	int lr_rm_num;
	int choice_option_nums[];
}
