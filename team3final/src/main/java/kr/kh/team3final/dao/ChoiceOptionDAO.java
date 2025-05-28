package kr.kh.team3final.dao;

import java.util.List;

import kr.kh.team3final.model.vo.ChoiceOptionVO;

public interface ChoiceOptionDAO {

	List<ChoiceOptionVO> selectChoiceOpotionList(int ld_num);


}
