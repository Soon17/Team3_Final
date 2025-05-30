package kr.kh.team3final.model.dto;

import lombok.Data;

@Data
public class RoomDTO {
	int rm_num;
	String rm_name;
	int rm_roomcount;
	int rm_person;
	int rm_price;
	String rm_infor;
}
