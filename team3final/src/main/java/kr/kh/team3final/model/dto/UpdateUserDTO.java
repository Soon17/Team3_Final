package kr.kh.team3final.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
	String me_number;
	String me_nick;
	String me_birthday;
	String me_gender;
}
