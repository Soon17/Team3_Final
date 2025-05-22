package kr.kh.team3final.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lodging_ReviewDTO {
	int rv_num;
	int rv_rating;
	Date rv_date;
	String rv_content;
	int ld_num;
	String ld_name;
	String ld_region;
	String me_nick;
	String rm_name;
}
