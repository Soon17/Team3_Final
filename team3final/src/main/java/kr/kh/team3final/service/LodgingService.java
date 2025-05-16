package kr.kh.team3final.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.LodgingDAO;
import kr.kh.team3final.model.vo.LodgingVO;

@Service
public class LodgingService {
	@Autowired
	LodgingDAO lodgingDao;

	public List<LodgingVO> getRegionLodgingList(int rg_num) {
		return lodgingDao.selectRegionLodgingList(rg_num);
	}

	public List<LodgingVO> getSearchlodgingList(String rg_name, String checkTime, String person) {
		String lr_checkin = getStartTime(checkTime);
		String lr_checkout =getEndTime(checkTime);
		int rm_person = Integer.parseInt(person.replaceAll("[^0-9]", ""));
		return lodgingDao.selectSearchLodgingList(rg_name,lr_checkin,lr_checkout,rm_person);
	}
	String getStartTime(String str){
		str = str.replaceAll("\\(.*?\\)", "").trim();
        String[] dates = str.split("~");
        String startTime = dates[0].trim().replace(".", "-");
		return startTime;
	}
	String getEndTime(String str){
		str = str.replaceAll("\\(.*?\\)", "").trim();
        String[] dates = str.split("~");
        String EndTime = dates[1].trim().replace(".", "-");
		return EndTime;
	}
}
