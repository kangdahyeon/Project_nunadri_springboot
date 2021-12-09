package com.springproject.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.springproject.role.Role;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	private String provider;
//	private String providerId;

	//검색용 필드
		private String searchCondition;
		private String searchKeyword;

	@Builder
	public MemberVO(String id, String pwd, String email, Role role, String name, String nickname, String provider) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.role = role;
		this.name = name;
		this.nickname = nickname;
		this.provider = provider;
	}

}
