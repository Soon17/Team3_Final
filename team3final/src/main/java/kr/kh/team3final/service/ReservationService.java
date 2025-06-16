package kr.kh.team3final.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ReservationDAO;
import kr.kh.team3final.model.vo.ReservationVO;

@Service
public class ReservationService {

    @Autowired
    ReservationDAO reservationDAO;

    public List<ReservationVO> selectList(int meNum) {

        return reservationDAO.selectList(meNum);
    }

    public List<ReservationVO> getLatestReservation(int meNum) {
        List<ReservationVO> reservations = reservationDAO.selectLatestReservation(meNum);

        LocalDate today = LocalDate.now();

        for (ReservationVO r : reservations) {
            Date checkinDateUtil = r.getLr_checkin();

            if (checkinDateUtil != null) {
                LocalDate checkinDate = checkinDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                boolean canCancel = checkinDate.isAfter(today);
                r.setCancelable(canCancel);
            } else {
                r.setCancelable(false);
            }
        }

        return reservations;
    }

    public boolean cancelHotel(int lr_num) {

        return reservationDAO.cancelHotel(lr_num, "예약취소") > 0;
    }

    public int getRmNum(int lrNum) {
        return reservationDAO.selectRmNum(lrNum);
    }

	public int getReNum(int rrNum) {
		return reservationDAO.selectReNum(rrNum);
	}

}
