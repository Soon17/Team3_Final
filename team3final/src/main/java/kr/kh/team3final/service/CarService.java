package kr.kh.team3final.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.team3final.dao.CarDAO;
import kr.kh.team3final.model.dto.CarDTO;
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

	public Map<String, Object> selectCarInfo(int ct_key, int cr_year, String cr_fuel_type, String cr_trans) {
		return carDao.selectCarInfo(ct_key, cr_year, cr_fuel_type, cr_trans);
	}

	public CarVO selectCarById(int cr_Id) {
		return carDao.selectCarById(cr_Id);
	}

	public List<CarVO> getRegionCarList(int rg_num, String type) {
		if (type.equals("rent-trend")) {
			return carDao.selectTrendCarList(rg_num);

		} else {
			return carDao.selectPriceCarList(rg_num);
		}
	}

	public CarVO getCar(int cr_id) {
		return carDao.selectCar(cr_id);
	}

	public boolean uploadCars(List<CarDTO> cars, int re_num) {
		boolean result = true;
		boolean res;

		// 숙소 썸네일 업로드
		String uploadDir = new File("").getAbsolutePath() +
				"/src/main/resources/static/imgs"; // 저장 경로
		for (CarDTO car : cars) {
			// 자동차들에 렌탈번호 부여
			car.setCr_re_num(re_num);

			MultipartFile thumb = car.getCr_thumbnail(); // 썸네일 파일
			String originalName = thumb.getOriginalFilename(); // 썸네일 원본 이름
			String uuid = UUID.randomUUID().toString();
			String uniqueName = uuid + "_" + originalName; // 썸네일 고유 이름

			File destination = new File(uploadDir, uniqueName); // 파일 객체 생성

			try {
				thumb.transferTo(destination); // 서버에 업로드
			} catch (IOException e) {
				System.out.println("차량 사진 서버 업로드 실패");
				e.printStackTrace();
				return false;
			}
			car.setThumbString(uniqueName);
			res = carDao.insertCar(car);
			if (!res)
				result = res;
		}
		return result;
	}

}
