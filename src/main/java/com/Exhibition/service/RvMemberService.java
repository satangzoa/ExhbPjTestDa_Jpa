package com.Exhibition.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Exhibition.entity.RvMember;
import com.Exhibition.exception.DataNotFoundException;
import com.Exhibition.repository.RvMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RvMemberService {

	private final RvMemberRepository rvMemberRepository;
	private final PasswordEncoder passwordEncoder;
	
public RvMember memberCreate(String muserid, String musername, String mpw, String memail) {
		
		
		RvMember rvmember = new RvMember();
		rvmember.setMuserid(muserid);
		rvmember.setMusername(musername);
//		rvmember.setMpw(mpw);
		
		//rvmember.setPassword(passwordEncoder.encode(mpw));
		rvmember.setMpw(passwordEncoder.encode(mpw));
		rvmember.setMemail(memail);
		rvMemberRepository.save(rvmember);
		
	    return rvmember;

		
	}
	
	public RvMember getMemberInfo(String muserid) {
		Optional<RvMember> optRvMember =  rvMemberRepository.findByMuserid(muserid) ;
		
		if(optRvMember.isPresent()) {
			RvMember rvMember = optRvMember.get();
			return rvMember;
		}else {
			throw new DataNotFoundException("유저를 찾을 수 없습니다.");
		}
	}
}
