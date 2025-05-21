package kr.kh.team3final.model.vo;

import java.util.List;

import lombok.Data;

@Data
public class RoomVO {
	int rm_num;
	String rm_name;
	int rm_price;
	int rm_person;
	String rm_infor;
	int rm_roomcount;
	int rm_ld_num;

	private List<ThumbnailVO> thumbList;
}
