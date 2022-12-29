package com.Exhibition.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Exhibition.dto.MemberForm;
import com.Exhibition.entity.RvMember;
import com.Exhibition.service.RvMemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final RvMemberService rvMemberService;
	

	
	@RequestMapping(value = "/")
	public String home() {
		
		return "redirect:index";
	}
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping(value = "/review")
	public String review() {
		return "review";
	}
	 
	

	@RequestMapping(value = "/join")
		public String join(MemberForm memberForm) {
					
			return "join_form";
		}
		
	@PostMapping(value = "/joinOk")
		public String joinOk(@Valid MemberForm memberForm, BindingResult bindingResult) {
			
			if(bindingResult.hasErrors()) {
				return "join_form";
			}
			
			
			try {
				rvMemberService.memberCreate(memberForm.getMuserid(), memberForm.getMusername(), memberForm.getMpw(), memberForm.getMemail());
			}catch(Exception e){
				e.printStackTrace();
				bindingResult.reject("joinFail", "이미 등록된 아이디입니다.");
				return "join_form";
			}
			return "redirect:index";
		}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login_form";
	}
	
	
}


