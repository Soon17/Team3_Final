package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReservationDAO;
import kr.kh.team3final.model.vo.ReservationVO;

@Service
public class ReservationService {
	
	@Autowired
	ReservationDAO reservationDAO;

	public List<ReservationVO> selectList(int meNum) {
		
		return reservationDAO.selectList(meNum);
	}
	
	public List<ReservationVO> getLatestReservation(int meNum) {
		
    return reservationDAO.selectLatestReservation(meNum);
	}

}
