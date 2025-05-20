package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.LodgingVO;

public interface LodgingDAO {

	List<LodgingVO> selectRegionLodgingList(int rg_num);

	List<LodgingVO> selectSearchLodgingList (@Param("rg_name") String rg_name,  @Param("lr_checkin") String lr_checkin,
	@Param("lr_checkout") String lr_checkout,  @Param("rm_person") int rm_person);

	List<LodgingVO> selectSearchAVGLodgingList(String rg_name, String lr_checkin, String lr_checkout, int rm_person);

	List<LodgingVO> selectSearchPriceLodgingList(String rg_name, String lr_checkin, String lr_checkout, int rm_person);

	List<LodgingVO> selectSearchRatingLodgingList(String rg_name, String lr_checkin, String lr_checkout, int rm_person);

	
}
