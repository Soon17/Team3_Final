package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.RentReservationDAO;
import kr.kh.team3final.model.vo.RentReservationVO;

@Service
public class RentReservationService {

	@Autowired
	RentReservationDAO rentReservationDAO;

	public List<RentReservationVO> selectRentList(int meNum) {
		return rentReservationDAO.selectRentList(meNum);
	}

	public List<RentReservationVO> getLatestRentReservation(int meNum) {
		return rentReservationDAO.getLatestRentReservation(meNum);
	}
	
}
