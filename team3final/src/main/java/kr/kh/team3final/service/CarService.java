package kr.kh.team3final.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.CarDAO;
import kr.kh.team3final.model.vo.CarVO;
import kr.kh.team3final.model.vo.SearchCriteria;

@Service
public class CarService {

	@Autowired
	CarDAO carDao;

	public List<CarVO> getSearchCarList(SearchCriteria cri) {
		String checkin = getStartTime(cri.getCheckTime());
		String checkout = getEndTime(cri.getCheckTime());
		return carDao.selectSearchCarList(checkin, checkout, cri);
	}

	private String getStartTime(String str) {
		str = str.replaceAll("\\(.*?\\)", "").trim();
		String[] dates = str.split("~");
		return dates[0].trim().replace(".", "-");
	}

	private String getEndTime(String str) {
		str = str.replaceAll("\\(.*?\\)", "").trim();
		String[] dates = str.split("~");
		return dates[1].trim().replace(".", "-");
	}

	public Map<String, Object> getCarInfo(int ct_key) {
		return carDao.selectCarInfo(ct_key);
	}
}
