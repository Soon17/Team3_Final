package kr.kh.team3final.dao;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.MemberVO;

public interface MemberDAO {

	MemberVO selectMember(String me_id);

	boolean insertMember(MemberVO member);

	MemberVO selectMemberByEmailAndProvider(@Param("me_email")String me_email, @Param("me_provider")String me_provider);

	boolean insertMemberByIp(MemberVO newUser);

}
