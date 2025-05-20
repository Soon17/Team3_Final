package kr.kh.team3final.dao;

import kr.kh.team3final.model.vo.MemberVO;

public interface MemberDAO {

	MemberVO selectMember(String me_id);

	boolean insertMember(MemberVO member);

	MemberVO selectMemberByEmail(String me_email);

	boolean insertMemberByIp(MemberVO newUser);

}
