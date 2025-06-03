package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.dto.AvailableCarDTO;
import kr.kh.team3final.model.vo.RentalVO;

public interface RentalDAO {
  List<AvailableCarDTO> selectAvailableCarList(
      @Param("ct_key") int ct_key,
      @Param("cr_year") int cr_year,
      @Param("cr_fuel_type") String cr_fuel_type,
      @Param("cr_trans") String cr_trans,
      @Param("checkin") String checkin,
      @Param("checkout") String checkout,
      @Param("rg_name") String rg_name);

  RentalVO selectRentalInfo(@Param("re_num") int re_num);
}