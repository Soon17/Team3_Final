package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.LodgingVO;

public interface LodgingDAO {

	List<LodgingVO> selectRegionLodgingList(int rg_num);

	LodgingVO allLodgingList(@Param("ld_num") int ld_num);

}
