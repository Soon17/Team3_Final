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

	public List<RoomVO> selectRoomListByLodging(int ld_num) {
		return roomDao.selectRoomListByLodging(ld_num);
	}

}
