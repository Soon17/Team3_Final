package kr.kh.team3final.dao;

import java.util.List;

import kr.kh.team3final.model.vo.RentReservationVO;

public interface RentReservationDAO {

	List<RentReservationVO> selectRentList(int meNum);

	List<RentReservationVO> getLatestRentReservation(int meNum);
	
}
