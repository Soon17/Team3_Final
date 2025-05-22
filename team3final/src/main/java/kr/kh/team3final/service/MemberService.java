package kr.kh.team3final.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.MemberDAO;
import kr.kh.team3final.model.vo.MemberVO;



@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;

	@Autowired
    PasswordEncoder passwordEncoder;

	public boolean checkId(String id) {
		return memberDAO.selectMember(id) == null;
	}

	public boolean signIn(MemberVO member) {
		if(member == null){
			return false;
		}

		//아이디 정규 표현식 확인
		if(!Pattern.matches("^[a-zA-Z0-9]{3,13}$", member.getMe_id())){
			return false;
		}
		//비번 정규 표현식 확인
		if(!Pattern.matches("^[a-zA-Z0-9!@#$]{3,15}$", member.getMe_pw())){
			return false;
		}
		//닉네임 정규 표현식 확인
		if(!Pattern.matches("^[a-zA-Z0-9가-힣]{2,10}$", member.getMe_nick())){
			return false;
		}
		//전화번호 정규 표현식 확인
		if(!Pattern.matches("^01[016789]-\\d{3,4}-\\d{4}$", member.getMe_number())){
			return false;
		}
		//생일 정규 표현식 확인
		if(!Pattern.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$", member.getMe_birthday())){
			return false;
		}
		
		//가입된 아이디 확인
		if(memberDAO.selectMember(member.getMe_id()) != null) {
			return false;
		}
		try {
			// 비밀번호 암호화
            String encodedPw = passwordEncoder.encode(member.getMe_pw());
            member.setMe_pw(encodedPw);  // 암호화된 비밀번호로 설정
			return memberDAO.insertMember(member);
		} catch (Exception e) {
			return false;
		}
	}

	public MemberVO getMemberByEmailAndProvider(String email, String provider) {
		return memberDAO.selectMemberByEmailAndProvider(email, provider);
	}

	public boolean insertMember(MemberVO newUser) {
		return memberDAO.insertMember(newUser);
	}

	public boolean insertMemberByIp(MemberVO newUser) {
		return memberDAO.insertMemberByIp(newUser);
	}

	
}
