package com.Exhibition.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class RvMember {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(unique = true)//유저네임 칼럼에 유니크 속성 부여 -> 중복된 값 저장 불허용
	private String muserid;//아이디
	
	private String musername;// 유저이름
	
	private String mpw; //비밀번호
	
	private String memail; //이메일
}

