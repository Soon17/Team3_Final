package kr.kh.team3final.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReviewDAO;
import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.dto.Rent_ReviewDTO;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.ReviewVO;

@Service
public class ReviewService {

	@Autowired
	ReviewDAO reviewDao;

	public List<Lodging_ReviewDTO> getSelectReviewList(int meNum) {
		return reviewDao.getSelectReviewList(meNum);
	}

	public List<Lodging_ReviewDTO> selectReview(String rv_table_name, int ld_num) {
		return reviewDao.selectReview(rv_table_name, ld_num);
	}

	public LodgingVO selectLodgingReviewStats(int ld_num) {
		return reviewDao.selectLodgingReviewStats(ld_num);
	}

	public List<Map<String, Object>> selectRatingCounts(int ld_num) {
		return reviewDao.selectRatingCounts(ld_num);
	}

	public int insertReview(ReviewVO review) {
		return reviewDao.insertReview(review);
	}

	public ReviewVO detailReview(int rvNumber, int me_num, String tableName) {
		return reviewDao.detailReview(rvNumber, me_num, tableName);
	}

	public int updateReview(ReviewVO review) {
		return reviewDao.updateReview(review);
	}

	public int deleteReview(int rvNumber, int me_num) {
		return reviewDao.deleteReview(rvNumber, me_num);
	}

	public List<Map<String, Object>> getRatingCount(int cr_re_num) {
		return reviewDao.selectRatingCount(cr_re_num);
	}

	public List<ReviewVO> rentalReviews(int re_num) {
		return reviewDao.rentalReviews(re_num);
	}

	public List<Rent_ReviewDTO> getSelectRentReviewList(int meNum) {
		return reviewDao.getSelectRentReviewList(meNum);
	}
}