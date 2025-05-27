package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class SearchCriteria {
	private String rg_name;
	private String checkTime;
	private String rm_person;

	// 공용 필터
	private String sort = "추천순";
	private String[] rating;
	private String[] type;
	private int avg;
	private int price;
	private String ld_name;

	// 렌트카 전용 필터
	private String[] ct_type; // 차량 타입
	private String ct_name; // 차량명 검색
	private String[] cr_fuel_type; // 연료
	private String[] cr_trans; // 변속기
}
