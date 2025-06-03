package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.dto.RoomDTO;
import kr.kh.team3final.model.vo.RoomVO;

public interface RoomDAO {

	RoomVO selectRoom(int rm_num);

	List<RoomVO> getAvailableRooms(
			@Param("ld_num") int ld_num,
			@Param("checkin") String checkin,
			@Param("checkout") String checkout,
			@Param("person") int person);

	boolean insertRoom(RoomDTO room);
}
