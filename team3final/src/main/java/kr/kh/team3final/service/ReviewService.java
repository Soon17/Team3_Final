package kr.kh.team3final.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReviewDAO;
import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.vo.LodgingVO;

@Service
public class ReviewService {

	@Autowired
	ReviewDAO reviewDAO;

	public List<Lodging_ReviewDTO> getSelectReviewList() {
		return reviewDAO.getSelectReviewList();
	}

	public List<Lodging_ReviewDTO> selectReview(String rv_table_name, int ld_num) {
		return reviewDAO.selectReview(rv_table_name, ld_num);
	}

	public LodgingVO selectLodgingReviewStats(int ld_num) {
		return reviewDAO.selectLodgingReviewStats(ld_num);
	}

	public List<Map<String, Object>> selectRatingCounts(int ld_num) {
		return reviewDAO.selectRatingCounts(ld_num);
	}
}