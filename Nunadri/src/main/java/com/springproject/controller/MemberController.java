package com.springproject.controller;

import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.role.Role;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import com.springproject.configuration.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberservice;
	

	//비밀번호 암호화 관련 필드
	private final PasswordEncoder encoder;
	
	
	//메인화면
	 @GetMapping("/")
	 public String main() {
		 return "view/main/index";
	 }
	 
	//회원가입
	@GetMapping("/signup")
	public String signUpView(Model model) {
		model.addAttribute("memberVO", new MemberVO());
		return "view/member/signup";
	}
	
	
	//회원가입 컨트롤러
	@PostMapping("/signup")
	public String signUp(@Valid MemberVO vo, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			System.out.println("========================" + bindingResult.getErrorCount() + "================");
			
			return "view/member/signup";
		}
		
		try {
			String password = vo.getPwd();
			vo.setPwd(encoder.encode(password));
			
			if(vo.getId().equals("admin")) {
				vo.setRole(Role.ADMIN);
			}else {
				vo.setRole(Role.USER);
			}
			
			memberservice.join(vo);
			
		}catch (IllegalStateException e) {
			//중복된 회원이 있을 시 예외발생
			model.addAttribute("errorMessage", e.getMessage());
			return "view/member/signup";
		}
		return "view/member/login";
		
	}
	
	
	//로그인 화면
	@GetMapping("/login")
	public String loginView(MemberVO m) {
		
		return "view/member/login";
	}
	

	//로그인 에러
	 @GetMapping("/login/error")
	    public String loginError(Model model) {
	        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
	        return "view/member/login";
	    }
	 

	 //아이디 찾기
	 @GetMapping("/findid")
	 public String findId() {
		 return "view/member/find_id";
	 }
	 
	 //비밀번호 찾기
	 @GetMapping("/findpwd")
	 public String findPwd() {
		 return "view/member/find_pw";
	 }
}
