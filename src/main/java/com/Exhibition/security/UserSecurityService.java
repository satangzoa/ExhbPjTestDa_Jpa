package com.Exhibition.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Exhibition.entity.RvMember;
import com.Exhibition.repository.RvMemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final RvMemberRepository rvMemberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String muserid) throws UsernameNotFoundException {

		Optional<RvMember> optSiteMember = rvMemberRepository.findByMuserid(muserid);
		
		if(optSiteMember.isEmpty()) { //참이면 가입된 아이디가 없다는 것
			throw new UsernameNotFoundException("등록되어 있지 않은 아이디입니다.");
		}
		
		RvMember rvMember = optSiteMember.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("admin".equals(muserid)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		return new User(rvMember.getMuserid(), rvMember.getMpw(), authorities);
	}

}


