package kr.kh.team3final.utils;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.kh.team3final.model.vo.MemberVO;
import lombok.Data;

@Data
public class CustomUser extends User {
	
	private MemberVO user;
	
	public CustomUser(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
		super(userId, password, authorities);
	}
	public CustomUser(MemberVO vo) {
		super(	vo.getMe_id(),
				vo.getMe_pw(), 
				Arrays.asList(new SimpleGrantedAuthority(vo.getMe_authority())));
		this.user = vo;
	}
}
