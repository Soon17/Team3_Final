package kr.kh.team3final.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RoomDTO {
	int rm_num;
	String rm_name;
	int rm_roomcount;
	int rm_person;
	int rm_price;
	String rm_infor;
	int rm_ld_num;

	List<MultipartFile> roomThumbnailList;
}
