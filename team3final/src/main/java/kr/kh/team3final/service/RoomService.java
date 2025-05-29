package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.RoomDAO;
import kr.kh.team3final.model.vo.RoomVO;

@Service
public class RoomService {
	@Autowired
	RoomDAO roomDao;

	public List<RoomVO> getAvailableRooms(int ld_num, String checkin, String checkout, int person) {
		return roomDao.getAvailableRooms(ld_num, checkin, checkout, person);
	}

	public RoomVO getRoom(int rm_num) {
		return roomDao.selectRoom(rm_num);
	}

}
