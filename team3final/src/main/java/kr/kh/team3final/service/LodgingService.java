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

	public List<LodgingVO> getSearchlodgingList(SearchCriteria cri) {
		String lr_checkin = getStartTime(cri.getCheckTime());
		String lr_checkout =getEndTime(cri.getCheckTime());
		int rm_person = Integer.parseInt(cri.getRm_person().replaceAll("[^0-9]", ""));
		switch (cri.getSort()) {
			case "추천순":
				return lodgingDao.selectSearchLodgingList(cri.getRg_name(),lr_checkin,lr_checkout,rm_person);
			case "평점순":
			return lodgingDao.selectSearchAVGLodgingList(cri.getRg_name(),lr_checkin,lr_checkout,rm_person);
			case "가격순":
			return lodgingDao.selectSearchPriceLodgingList(cri.getRg_name(),lr_checkin,lr_checkout,rm_person);
			case "성급순":			
			return lodgingDao.selectSearchRatingLodgingList(cri.getRg_name(),lr_checkin,lr_checkout,rm_person);
			default:
				return lodgingDao.selectSearchLodgingList(cri.getRg_name(),lr_checkin,lr_checkout,rm_person);
		}
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
