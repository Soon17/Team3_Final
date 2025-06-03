package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.OptionDAO;
import kr.kh.team3final.model.vo.ChoiceOptionVO;
import kr.kh.team3final.model.vo.DefaultOptionVO;


@Service
public class OptionService {

	@Autowired
	OptionDAO optionDAO;

	public List<ChoiceOptionVO> getChoiceOpotionList(int ld_num) {
		return optionDAO.selectChoiceOpotionList(ld_num);
	}

	public List<String> getDefaultOptions() {
		List<String> defaultOptions = optionDAO.selectDefaultOptions();
		return defaultOptions;
	}

	public void uploadChoiceOption(int do_num, int ld_num) {
		optionDAO.insertChoiceOption(do_num, ld_num);
	}

	public void uploadDefaultOption(DefaultOptionVO defaultOption) {
		optionDAO.insertDefaultOption(defaultOption);
	}
	
}
