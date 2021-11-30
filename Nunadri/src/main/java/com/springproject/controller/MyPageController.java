package com.springproject.controller;



import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.impl.UserDetailsServiceImpl;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {


	private final MemberService memberservice;

	private final PasswordEncoder encoder;

	private final UserDetailsServiceImpl UserDetailsServiceImpl;


	//마이페이지 메인
	@GetMapping("/mypage")
	public String myPage() {
		return "view/member/mypage/mypage_main";
	}


	//회원정보 수정 페이지
	@GetMapping("/mypagemodify")
	public String myPage(@AuthenticationPrincipal SecurityUser user, Model model) {
		MemberVO member = memberservice.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);
		return "view/member/mypage/member_modify";
	}


	//비밀번호 변경 페이지
	@GetMapping("/changePassword")
	public String changePassword() {
		return "view/member/mypage/change_pw";
	}



	//회원정보 수정
	@PostMapping("/update")
	@ResponseBody
	public String updateMember(@AuthenticationPrincipal SecurityUser user, MemberVO vo, HttpSession session) {

		//		System.out.println("vo.getEmail() : " +  vo.getEmail());
		//		System.out.println( " memberservice.findId(user.getId()).getEmail() : " + memberservice.findId(user.getId()).getEmail());
		//		System.out.println("=================================================");
		//		System.out.println( "vo.getNickname() : " + vo.getNickname());
		//		System.out.println("user.getNickname() : " +user.getNickname());

		System.out.println(vo.toString());

		
			if(memberservice.findEmail(vo.getEmail()) != null && !vo.getEmail().equals(memberservice.findId(user.getId()).getEmail())) {
				throw new IllegalStateException("이미 가입된 이메일입니다.");
			}else if(memberservice.findNickname(vo.getNickname()) != null && !vo.getNickname().equals(user.getNickname())) {

				throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
			}else {

				memberservice.updateMember(vo);
				System.out.println("정보 업데이트");
				// user에 직접 들어갈 수 있도록 여기서 데이터 입력해줌
				UserDetails userDetails = UserDetailsServiceImpl.loadUserByUsername(vo.getId());

				//세션 등록
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);

				session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);


				return "/mypage";
			}

	}

	//회원 탈퇴
	@GetMapping("/deleteMember")
	public String deleteMember(@AuthenticationPrincipal SecurityUser user) {



		memberservice.deleteMember(user.getId());

		//세션 종료 후 로그아웃
		SecurityContextHolder.clearContext();

		return "redirect:/";
	}


	//비밀번호 변경
	@PostMapping("/changePassword")
	@ResponseBody
	public String checkPassword(@AuthenticationPrincipal SecurityUser user, String pwd) {


		MemberVO member = new MemberVO();

		member.setId(user.getId());
		pwd = encoder.encode(pwd);
		member.setPwd(pwd);

		memberservice.updatePwd(member);

		return "/mypage";
	}
}