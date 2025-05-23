package kr.kh.team3final.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.dto.Lodging_ReviewDTO;
import kr.kh.team3final.model.vo.LodgingVO;

public interface ReviewDAO {

	List<Lodging_ReviewDTO> getSelectReviewList();

	List<Lodging_ReviewDTO> selectReview(@Param("rv_table_name") String rv_table_name, @Param("ld_num") int ld_num);

	LodgingVO selectLodgingReviewStats(@Param("ld_num") int ld_num);

	List<Map<String, Object>> selectRatingCounts(@Param("ld_num") int ld_num);

}
