package kr.kh.team3final.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.MemberDAO;



@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;

	public boolean checkId(String id) {
		return memberDAO.selectMember(id) == null;
	}

	
}
