package kr.kh.team3final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.MemberDAO;
import kr.kh.team3final.utils.CustomUser;
import kr.kh.team3final.model.vo.MemberVO;

@Service
public class MemberDetailService implements UserDetailsService{

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		MemberVO member = memberDao.selectMember(userId);
		if (member == null || member.getMe_del().equals("Y")) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
		}
		return new CustomUser(member);
	}

}