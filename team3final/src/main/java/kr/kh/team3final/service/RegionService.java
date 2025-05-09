package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.RegionDAO;
import kr.kh.team3final.model.vo.RegionVO;

@Service
public class RegionService {
	@Autowired
	RegionDAO regionDao;

	public List<RegionVO> getRegionList() {
		return regionDao.selectRegionList();
	}
}
