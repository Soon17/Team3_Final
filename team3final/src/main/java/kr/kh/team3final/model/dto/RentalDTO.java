package kr.kh.team3final.model.dto;

import java.util.List;

import kr.kh.team3final.model.vo.CarVO;
import lombok.Data;

@Data
public class RentalDTO {
	int re_num;
	String re_name;
	String re_number;
	String re_region;
	int re_me_num;
	int re_rg_num;

	List<CarDTO> cars;
}