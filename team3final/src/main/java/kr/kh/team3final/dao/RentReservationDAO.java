package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.RentReservationVO;

public interface RentReservationDAO {

	List<RentReservationVO> selectRentList(int meNum);

	List<RentReservationVO> getLatestRentReservation(int meNum);

	int cancelRent(@Param("rr_num")int rr_num, @Param("state")String string);
	
}
