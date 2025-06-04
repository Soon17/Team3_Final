package kr.kh.team3final.model.vo;

import lombok.Data;

@Data
public class CarTypeVO {
	int ct_key;
	String ct_name;
	String ct_type;

	public String toString(){
		return 
			"{\"ct_key\" : " + ct_key +
			",\"ct_name\" : \"" + ct_type + "\"" + 
			",\"ct_type\" : \"" + ct_name + "\"}";
	}
}
