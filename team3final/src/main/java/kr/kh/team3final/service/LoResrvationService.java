package kr.kh.team3final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.LoResrvationDAO;
import kr.kh.team3final.model.vo.LoResrvationVO;


@Service
public class LoResrvationService {

	@Autowired
	LoResrvationDAO loResrvationDAO;

	public void insertLoReservation(LoResrvationVO lr) {
		loResrvationDAO.insertLoReservation(lr);
		if(lr.getChoice_option_nums() != null && lr.getChoice_option_nums().length !=0 ){
			loResrvationDAO.insertUserOption(lr);
		}
	}

}