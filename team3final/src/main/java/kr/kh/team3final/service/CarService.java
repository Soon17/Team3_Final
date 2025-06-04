package kr.kh.team3final.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
		str = str.replaceAll("\(.*?\)", "").trim();
		String[] dates = str.split("~");
		return dates[0].trim().replace(".", "-");
	}

	private String getEndTime(String str) {
		str = str.replaceAll("\(.*?\)", "").trim();
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

		for (CarDTO car : cars) {
			// 자동차들에 렌탈번호 부여
			car.setCr_re_num(re_num);

			// 숙소 썸네일 업로드
			String uploadDir = new File("").getAbsolutePath() +
					"/team3final/src/main/resources/static/img";
			String originalName = car.getCr_thumbnail().getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String uniqueName = uuid + "_" + originalName;

			File destination = new File(uploadDir, uniqueName);
			car.getCr_thumbnail().transferTo(destination);
			System.out.println("차량 썸네일 파일: " + uniqueName);
			car.setCr_thumbnail(uniqueName);
			res = carDao.insertCar(car);
			if (!res)
				result = res;
		}
		return result;
	}

}
