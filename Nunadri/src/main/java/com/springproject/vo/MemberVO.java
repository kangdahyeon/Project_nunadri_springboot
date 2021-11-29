package com.springproject.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.springproject.role.Role;

import lombok.Data;

@Data
public class MemberVO {
	
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String id;
	
	@Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
	private String pwd;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotEmpty(message = "주소는 필수 입력 값입니다.")
	private String address;
	
	@NotEmpty(message = "상세 주소는 필수 입력 값입니다.")
	private String addressDetail;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	private String nickname;
	
	
	private Role role;
	
	private String profile;
	

}
