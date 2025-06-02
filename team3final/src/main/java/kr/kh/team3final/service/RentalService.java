package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.RentalDAO;
import kr.kh.team3final.model.dto.AvailableCarDTO;

@Service
public class RentalService {

	@Autowired
	RentalDAO rentalDao;

	public List<AvailableCarDTO> getAvailableCarList(int ct_key, int cr_year, String cr_fuel_type, String cr_trans,
			String checkin, String checkout, String rg_name) {
		return rentalDao.selectAvailableCarList(ct_key, cr_year, cr_fuel_type, cr_trans, checkin, checkout, rg_name);
	}

}
