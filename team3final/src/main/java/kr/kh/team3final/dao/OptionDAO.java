package kr.kh.team3final.dao;

import java.util.List;

import kr.kh.team3final.model.vo.ChoiceOptionVO;
import kr.kh.team3final.model.vo.DefaultOptionVO;

public interface OptionDAO {

	List<ChoiceOptionVO> selectChoiceOpotionList(int ld_num);

	void insertChoiceOption(int do_num, int ld_num);

	List<String> selectDefaultOptions();
	
	void insertDefaultOption(DefaultOptionVO defaultOption);
}
