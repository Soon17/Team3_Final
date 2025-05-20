package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class SearchCriteria {
    private String rg_name;
    private String checkTime;
    private String rm_person;
    private String sort="추천순";
	private String[] rating;
	private String[] type;
	private int avg;
	private int price;
}
