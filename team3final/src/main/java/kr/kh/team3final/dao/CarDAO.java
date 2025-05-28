package kr.kh.team3final.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.CarVO;
import kr.kh.team3final.model.vo.SearchCriteria;

public interface CarDAO {

  List<CarVO> selectSearchCarList(@Param("checkin") String checkin,
      @Param("checkout") String checkout,
      @Param("cri") SearchCriteria cri);

  Map<String, Object> selectCarInfo(@Param("ct_key") int ct_key);

}
