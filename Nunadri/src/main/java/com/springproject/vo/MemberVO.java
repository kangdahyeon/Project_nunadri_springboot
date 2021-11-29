package com.springproject.vo;

import com.springproject.role.Role;

import lombok.Data;

@Data
public class MemberVO {
   
   private String id;
   
   private String pwd;
   
   private String email;
   
   private String address;
   
   private String addressDetail;
   
   private String name;
   
   private String nickname;
   
   private String phone;
   
   private Role role;
   
   private String profile;
   

}