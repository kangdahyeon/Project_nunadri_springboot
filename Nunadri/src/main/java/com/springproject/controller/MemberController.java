package com.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.configuration.auth.PrincipalDetails;
import com.springproject.role.Role;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private PasswordEncoder encoder;
	
	 @GetMapping("/")
	 public String main() {
		
		 
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
		}else {
			vo.setRole(Role.USER);	
		}
		
		memberservice.join(vo);
		return "view/member/login";
	}
	
	
	
	@GetMapping("/login")
	public String loginView(MemberVO m) {
		
		return "view/member/login";
	}
	
//	@PostMapping("/login")
//	public String login(MemberVO vo, Model model) {
//		System.out.println(" asdadas");
//		System.out.println(vo.getNickname());
//		model.addAttribute("nickname", vo.getNickname());
//		return "view/main/index";
//	}
	
	
	 @GetMapping("/login/error")
	    public String loginError(Model model) {
	        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
	        return "view/member/login";
	    }
	 
	 @GetMapping("/board")
	 public String board(@AuthenticationPrincipal SecurityUser user, Model model) {
		 System.out.println(user.getId() + "," + user.getNickname() + "," + user.getUsername());
		 MemberVO member = memberservice.findId(user.getId());
		 System.out.println(member.getAddress() + ", " + member.getNickname());
		 model.addAttribute("mem", member);
		 return "view/community/free/boarder_Communication";
	 }
	
	
}
