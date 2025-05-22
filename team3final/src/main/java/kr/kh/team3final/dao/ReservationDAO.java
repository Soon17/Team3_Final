package kr.kh.team3final.dao;

import java.util.List;

import kr.kh.team3final.model.vo.ReservationVO;

public interface ReservationDAO {

	List<ReservationVO> selectList(int meNum);

	List<ReservationVO> selectLatestReservation(int meNum);
	
}
