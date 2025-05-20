package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReviewDAO;
import kr.kh.team3final.model.dto.Lodging_ReviewDTO;

@Service
public class ReviewService {
	
	@Autowired
	ReviewDAO reviewDAO;

	public List<Lodging_ReviewDTO> getSelectReviewList() {
		return reviewDAO.getSelectReviewList();
	}

}
