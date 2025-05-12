package kr.kh.team3final.dao;

import java.util.List;

import kr.kh.team3final.model.vo.LodgingVO;

public interface LodgingDAO {

	List<LodgingVO> selectRegionLodgingList(int rg_num);

	
}
