package kr.kh.team3final.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LodgingDTO {
	int ld_num;
	String ld_type;
	int ld_rating;
	int ld_rg_num;
	int ld_me_num;
	String ld_name;

	int ld_meal_price;
	
	String ld_region;
	String ld_number;
	String ld_infor;

	List<RoomDTO> rooms;
	List<MultipartFile> lodgingThumbnailList;
}
