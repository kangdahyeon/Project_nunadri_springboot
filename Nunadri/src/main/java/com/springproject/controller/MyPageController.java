package com.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Controller
public class MyPageController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private PasswordEncoder encoder;
	
	 @GetMapping("/mypage")
	  public String myPageMain(@AuthenticationPrincipal SecurityUser user, Model model) {
		  
		  MemberVO member = memberservice.getMemberInfo(user.getId());
//		  model.addAttribute("memberInfo", member);
//		  user = (SecurityUser)userDetailsServiceImpl.loadUserByUsername(member.getId());
		  System.out.println(user.getId() + "2");
		  System.out.println(user.getPassword() + "2");
		  System.out.println(user.getNickname() + "2");
		  return "view/member/mypage/mypage_main";
	  }
	
	  @GetMapping("/mypagemodify")
	  public String myPage(@AuthenticationPrincipal SecurityUser user, Model model) {
		  
		  MemberVO member = memberservice.getMemberInfo(user.getId());
		  model.addAttribute("memberInfo", member);
//		  user = (SecurityUser)userDetailsServiceImpl.loadUserByUsername(member.getId());
//		  model.addAttribute("memberInfo", member);
		  System.out.println(user.getId() + "3");
		  System.out.println(user.getPassword() + "3");
		  System.out.println(user.getNickname() + "3");
		  return "view/member/mypage/member_modify";
	  }
	  
//		// 회원 탈퇴 get
//		@RequestMapping(value="/deleteMemberView", method = RequestMethod.GET)
//		public String deleteMemberView() throws Exception{
//			return "view/member/login";
//		}
	  
		// 회원 탈퇴 post
	   @GetMapping(value="/deleteMember")
		public String deleteMember(@AuthenticationPrincipal SecurityUser user) {
		   
		
	
			memberservice.deleteMember(user.getId());
				
			//세션 종료 후 로그아웃
				SecurityContextHolder.clearContext();
			
			return "redirect:/";
		}
	  
	   @PostMapping(value="/updateMember")
	   public String updateMember(@AuthenticationPrincipal SecurityUser user, MemberVO member, Model model){
//		   System.out.println("5");
//
//		   memberservice.updateMember(member);
//	        // 여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됐음
//	        // 하지만 세션값은 변경되지 않은 상태이기때문에 세션값 갱신이 필요함
//		   System.out.println("0");
//		   // 세션 등록
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        
//	        SecurityUser modifiyUser = (SecurityUser)userDetailsServiceImpl.loadUserByUsername(authentication.getName());
//	        
//	        System.out.println(modifiyUser.getNickname());
//	        
////	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
////	        SecurityContextHolder.getContext().setAuthentication(authentication);
//	        
//	        
//	        //Authentication newAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), modifiyUser.getAuthorities()));
//	        Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), modifiyUser.getAuthorities());
//	        
//	        System.out.println("1");
//	        SecurityContextHolder.getContext().setAuthentication(newAuth);
//	        RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT", newAuth, RequestAttributes.SCOPE_SESSION);
//	        
//	        System.out.println("2");
//	        System.out.println(member.getPwd());
//	        
//	        String password = member.getPwd();
//	        
//	        member.setPwd(encoder.encode(password));
//	        
//	        memberservice.updateMember(member);
//		   
//		   System.out.println(member.getPwd());
//			//세션 종료 후 로그아웃
//		    SecurityContextHolder.clearContext();
		   
			  System.out.println(user.getId() + "8");
			  System.out.println(user.getPassword() + "8");
			  System.out.println(user.getNickname() + "8");
			  
		   try {  
//			   if(user.getPassword() == null || "".equals(user.getPassword())) {
				   if(member.getPwd() == null || "".equals(member.getPwd())) {
		
		   } else {
			   System.out.println(member.getPwd());
			   String password = member.getPwd();
			   System.out.println(member.getPwd());
			   member.setPwd(encoder.encode(password));
			   System.out.println("1");
			   memberservice.updateMember(member);
		   	}
		   }
		   catch (Exception e) {
			   model.addAttribute("errorMessage", e.getMessage());
			   System.out.println(e.getMessage()+"5");
			   return "redirect:/view/member/mypage/member_modify";
		   }
//		    SecurityContextHolder.se
		   System.out.println(member.getPwd());
		   System.out.println(user.getPassword()+"6");
	        return "redirect:/mypage";
	    }
	   
//		@GetMapping("/updateMember/error")
//		public String updateMemberError(Model model) {
//			model.addAttribute("updateMemberError", "비밀번호를 입력해주세요");
//			
//			return "view/member/mypage/member_modify";
//	}
//	 

	 
	  
}
