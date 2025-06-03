package kr.kh.team3final.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReReservationDAO;
import kr.kh.team3final.model.vo.ReReservationVO;


@Service
public class ReReservationService {

	@Autowired
	ReReservationDAO reReservationDAO;

	public void insertReReservation(ReReservationVO rr) {
		reReservationDAO.insertCp(rr);
		reReservationDAO.insertReReservation(rr);
	}



}
