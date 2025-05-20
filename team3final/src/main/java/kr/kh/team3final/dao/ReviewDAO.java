package kr.kh.team3final.dao;

import java.util.List;

import kr.kh.team3final.model.dto.Lodging_ReviewDTO;

public interface ReviewDAO {

	List<Lodging_ReviewDTO> getSelectReviewList();
	
}
