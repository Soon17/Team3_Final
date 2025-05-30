package kr.kh.team3final.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReviewDAO;
import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.ReviewVO;

@Service
public class ReviewService {

	@Autowired
	ReviewDAO reviewDAO;

	public List<Lodging_ReviewDTO> getSelectReviewList(int meNum) {
		return reviewDAO.getSelectReviewList(meNum);
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

	public int insertReview(ReviewVO review) {
		return reviewDAO.insertReview(review);
	}

	public ReviewVO detailReview(int rvNumber, int me_num) {
		return reviewDAO.detailReview(rvNumber, me_num);
	}

	public int updateReview(ReviewVO review) {
		return reviewDAO.updateReview(review);
	}

	public int deleteReview(int rvNumber, int me_num) {
		return reviewDAO.deleteReview(rvNumber, me_num);
	}
}