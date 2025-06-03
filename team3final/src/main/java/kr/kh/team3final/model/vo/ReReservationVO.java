package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class ReReservationVO {
	int rr_num;
	String rr_checkIn;
	String rr_checkOut;
	int rr_price;
	String rr_date;
	String rr_name;
	String rr_number;
	int rr_birth;
	int cp_num;
	int rr_me_num;

	int cr_id;
	int ir_num;
}
