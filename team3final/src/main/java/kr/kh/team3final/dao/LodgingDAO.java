package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.SearchCriteria;

public interface LodgingDAO {

	List<LodgingVO> selectReviewLodgingList(int rg_num);

	List<LodgingVO> selectPriceLodgingList(int rg_num);

	List<LodgingVO> selectReservationLodgingList(int rg_num);
	
	LodgingVO allLodgingList(@Param("ld_num") int ld_num);

	List<LodgingVO> selectRegionSwitchLodgingList(
			String lr_checkin, String lr_checkout, int rm_person, SearchCriteria cri);

	LodgingVO selectLodging(int ld_num);

	int getMealPrice(int ldNum);

	

	

}
