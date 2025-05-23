package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.LodgingDAO;
import kr.kh.team3final.model.vo.LodgingVO;
import kr.kh.team3final.model.vo.SearchCriteria;

@Service
public class LodgingService {
	@Autowired
	LodgingDAO lodgingDao;

	public List<LodgingVO> getRegionLodgingList(int rg_num) {
		return lodgingDao.selectRegionLodgingList(rg_num);
	}

	public LodgingVO allLodgingList(int ld_num) {
		return lodgingDao.allLodgingList(ld_num);
	}

	public List<LodgingVO> getSearchlodgingList(SearchCriteria cri) {
		String lr_checkin = getStartTime(cri.getCheckTime());
		String lr_checkout = getEndTime(cri.getCheckTime());
		int rm_person = Integer.parseInt(cri.getRm_person().replaceAll("[^0-9]", ""));
		// sort만 판단
		return lodgingDao.selectRegionSwitchLodgingList(lr_checkin, lr_checkout, rm_person, cri);

		// //다 있다는 가정하에 매퍼에서 if문?
	}

	String getStartTime(String str) {
		str = str.replaceAll("\\(.*?\\)", "").trim();
		String[] dates = str.split("~");
		String startTime = dates[0].trim().replace(".", "-");
		return startTime;
	}

	String getEndTime(String str) {
		str = str.replaceAll("\\(.*?\\)", "").trim();
		String[] dates = str.split("~");
		String EndTime = dates[1].trim().replace(".", "-");
		return EndTime;
	}

	public LodgingVO getLodging(int ld_num) {
		return lodgingDao.selectLodging(ld_num);
	}
}
