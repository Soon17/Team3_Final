package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.dto.AvailableCarDTO;

public interface RentalDAO {
  List<AvailableCarDTO> selectAvailableCarList(
      @Param("ct_key") int ct_key,
      @Param("cr_year") int cr_year,
      @Param("cr_fuel_type") String cr_fuel_type,
      @Param("cr_trans") String cr_trans);
}