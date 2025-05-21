package kr.kh.team3final.dao;

import java.util.List;


import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.SearchCriteria;

public interface LodgingDAO {

	List<LodgingVO> selectRegionLodgingList(int rg_num);

	List<LodgingVO> selectRegionSwitchLodgingList(String lr_checkin, String lr_checkout, int rm_person,SearchCriteria cri);

	
}
