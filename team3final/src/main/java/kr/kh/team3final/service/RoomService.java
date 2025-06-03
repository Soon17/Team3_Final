package kr.kh.team3final.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.team3final.dao.RoomDAO;
import kr.kh.team3final.model.dto.RoomDTO;
import kr.kh.team3final.model.vo.RoomVO;

@Service
public class RoomService {
	@Autowired
	RoomDAO roomDao;
	@Autowired
	ThumbnailService thumbnailService;

	public List<RoomVO> getAvailableRooms(int ld_num, String checkin, String checkout, int person) {
		return roomDao.getAvailableRooms(ld_num, checkin, checkout, person);
	}

	public RoomVO getRoom(int rm_num) {
		return roomDao.selectRoom(rm_num);
	}

	public boolean uploadRooms(List<RoomDTO> rooms, int ld_num) throws IOException{
		boolean result = true;
		boolean res;
		String uploadDir = new File("").getAbsolutePath() +
		"/team3final/src/main/resources/static/img";
		for(RoomDTO room : rooms){
			// 해당 숙소의 룸들에 rm_ld_num지정
			room.setRm_ld_num(ld_num);

			res = roomDao.insertRoom(room);
			if(!res) result = res;

			boolean uploadRMTH = false;

			/* 룸 썸네일 업로드 */
			for (MultipartFile file : room.getRoomThumbnailList()) {
				if (!file.isEmpty()) {
					String originalName = file.getOriginalFilename();
					String uuid = UUID.randomUUID().toString();
					String uniqueName = uuid + "_" + originalName;
					
					File destination = new File(uploadDir, uniqueName);
					file.transferTo(destination);
					System.out.println("룸 썸네일 업로드 성공: " + uniqueName);
					uploadRMTH = thumbnailService.uploadRMTH(room.getRm_num(), uniqueName);
				}
			}
		}
		return result;
	}

}
