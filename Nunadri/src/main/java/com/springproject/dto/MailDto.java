package com.springproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* 이메일 발송 DTO  */
@Getter
@Setter
@NoArgsConstructor
public class MailDto {
	private String address;
	private String title;
	private String message;
	//private MultipartFile file;

}
