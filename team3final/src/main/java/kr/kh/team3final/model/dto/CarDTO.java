package kr.kh.team3final.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CarDTO {
	int cr_id;
	int cr_year;				// 차량 연식//
	String cr_fuel_type;		// 연료 형태
	String cr_trans;			// 주행 형태
	int cr_price;				// 기본 요금//
	int cr_count;				// 차량 대수//
	int cr_re_num;				// 렌트업체 번호//
	int cr_ct_key;				// 차량 타입//
	MultipartFile cr_thumbnail;	// 차량 사진//
}
