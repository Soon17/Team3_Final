package kr.kh.team3final.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.RentReservationDAO;
import kr.kh.team3final.model.vo.RentReservationVO;

@Service
public class RentReservationService {

	@Autowired
	RentReservationDAO rentReservationDAO;

	public List<RentReservationVO> selectRentList(int meNum) {
		return rentReservationDAO.selectRentList(meNum);
	}

	public List<RentReservationVO> getLatestRentReservation(int meNum) {
    List<RentReservationVO> reservations = rentReservationDAO.getLatestRentReservation(meNum);

    LocalDate today = LocalDate.now();

    for (RentReservationVO r : reservations) {
        Date checkinDateUtil = r.getRr_checkIn();

        if (checkinDateUtil != null) {
            LocalDate checkinDate = checkinDateUtil.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

            boolean canCancel = checkinDate.isAfter(today);
            r.setCancelable(canCancel);
        } else {
            r.setCancelable(false);
        }
    }

    return reservations;
}

	public boolean cancelRent(int rr_num) {
		return rentReservationDAO.cancelRent(rr_num, "예약취소") > 0;
	}
	
}
