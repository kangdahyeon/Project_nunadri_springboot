package com.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springproject.role.Role;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/")
	public String index() {
	    return "view/main/index";
	}
	
	@GetMapping("/signup")
	public String signUpView() {
		return "view/member/signup";
	}
	
	@PostMapping("/signup")
	public String signUp(MemberVO vo) {
		String password = vo.getPwd();
		
		vo.setPwd(encoder.encode(password));
		
		if(vo.getId().equals("admin")) {
			vo.setRole(Role.ADMIN);
		} else {
			vo.setRole(Role.USER);
		}
		
		memberservice.join(vo);
		return "view/member/login";
	}
	
	@GetMapping("/login")
	public String loginView(Model model) {
		return "view/member/login";
	}
	
	
//	@PostMapping("/login")
//	public String login() {
//		return "view/main/index";
//	}
	
	@GetMapping("/login/error")
		public String loginError(Model model) {
			model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
			return "view/member/login";
	}
	
}
