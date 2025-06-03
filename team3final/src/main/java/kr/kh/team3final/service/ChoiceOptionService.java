package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ChoiceOptionDAO;
import kr.kh.team3final.model.vo.ChoiceOptionVO;


@Service
public class ChoiceOptionService {

	@Autowired
	ChoiceOptionDAO choiceOptionDAO;

	public List<ChoiceOptionVO> getChoiceOpotionList(int ld_num) {
		return choiceOptionDAO.selectChoiceOpotionList(ld_num);
	}

	public void uploadChoiceOption(int do_num, int ld_num) {
		choiceOptionDAO.insertChoiceOption(do_num, ld_num);
	}
	
}
