package com.springproject.controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {

   
   private final MemberService memberservice;
   
   private final PasswordEncoder encoder;


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
   public String updateMember(MemberVO member) {
      memberservice.updateMember(member);
    //세션 종료 후 로그아웃
      SecurityContextHolder.clearContext();
   
      return "redirect:/mypage";
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