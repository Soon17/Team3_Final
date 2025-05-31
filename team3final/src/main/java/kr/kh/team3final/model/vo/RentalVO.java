package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class RentalVO {
	int reNum;
	String reName;
	String reNumber;
	String reRegion;
	int reMeNum;
	int reRgNum;

	Double avg_rating;
	int review_count;
}