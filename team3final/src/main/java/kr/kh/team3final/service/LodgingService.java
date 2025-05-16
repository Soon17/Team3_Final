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

	public List<LodgingVO> allLodgingList(int ld_num) {
		return lodgingDao.allLodgingList(ld_num);
	}
}
