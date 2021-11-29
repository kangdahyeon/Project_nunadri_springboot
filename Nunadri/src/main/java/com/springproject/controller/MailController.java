package com.springproject.controller;

import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.dto.MailDto;
import com.springproject.impl.SendEmailService;
import com.springproject.service.MemberService;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
/*생성자를 통해서 객체 주입 
 * 참조자료 : https://madplay.github.io/post/why-constructor-injection-is-better-than-field-injection
 */
@RequiredArgsConstructor
public class MailController {

	private final SendEmailService mailService;

	private final MemberService memberservice;

	private final PasswordEncoder encoder;



	//이메일 발송(아이디 찾기)
	@PostMapping("/checkMail")
	@ResponseBody
	public String sendEmail(String email, String name) {
		//유저 조회
		MemberVO member = memberservice.findEmail(email);
		String key="";
		try {
			//유저정보가 없을 시 에러를 던진다.
			if(member == null || !(member.getName().equals(name))) {
				throw new Exception();
			}
			//유저가 존재할 경우 6자리 난수 설정 후 코드 발송
			else {
				Random random = new Random();
				for(int i=0; i<6; i++) {
					int index = random.nextInt(10);
					key+=index;
				}
				
				//보낼 이메일 설정
				MailDto MailDto = new MailDto();

				MailDto.setAddress(email); //이메일 주소
				MailDto.setTitle("인증발송한 이메일입니다."); //제목
				MailDto.setMessage("인증번호: " + key); //내용

				mailService.sendMail(MailDto);
				return key; //인증코드 리턴
			}
		}catch (Exception e) {
			return e.getMessage();
		}
	
	}


	//아이디 이메일로 발송
	@PostMapping("/sendId")
	@ResponseBody
	public String checkCode(String email) {
		//유저 조회
		MemberVO member = memberservice.findEmail(email);

		MailDto MailDto = new MailDto();
		MailDto.setAddress(email);
		MailDto.setTitle("인증발송한 이메일입니다.");
		MailDto.setMessage("id: " + member.getId());

		mailService.sendMail(MailDto);
		return "/login";
	}



	//이메일 발송(비밀번호 찾기)
	@PostMapping("/checkPassword")
	@ResponseBody
	public String checkEmail(String email, String id) {
		//유저 조회
		MemberVO member = memberservice.findEmail(email);
		String key="";
		
		try {
			//유저정보가 없을 시 에러를 던진다.
			if(member == null || !(member.getId().equals(id))) {
				throw new Exception();
			}
			else {
				Random random = new Random();
				
				for(int i=0; i<6; i++) {
					int index = random.nextInt(10);
					
					key+=index;
				}
				//보낼 이메일 설정
				MailDto MailDto = new MailDto();
				
				MailDto.setAddress(email);
				MailDto.setTitle("인증발송한 이메일입니다.");
				MailDto.setMessage("인증번호: " + key);
				
				mailService.sendMail(MailDto);
				return key;
			}
			
		}catch (Exception e) {
			return e.getMessage();
		}
	}

	
	
	//임시비밀번호 이메일로 발송
	@PostMapping("/checkPwdCode")
	@ResponseBody
	public String sendTempPwd(MemberVO vo) {
		//난수 생성 알파벳 4글자 + 4자리 숫자
		Random random = new Random();
		String tempPw = "";
		for(int i =0; i<4;i++) {
			int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
			tempPw+=(char)index;
		}
		int numIndex=random.nextInt(9999)+1000; //4자리 랜덤 정수를 생성
		tempPw+=numIndex;

		//비밀번호 암호화
		String pwd = encoder.encode(tempPw);
		vo.setPwd(pwd);
		//유저 비밀번호 임시번호로 업데이트
		memberservice.updatePwd(vo);


		MailDto MailDto = new MailDto();

		MailDto.setAddress(vo.getEmail());
		MailDto.setTitle("인증발송한 이메일입니다.");
		MailDto.setMessage("임시비밀번호: " + tempPw);

		mailService.sendMail(MailDto);
		
		
		return "/login";
		
	}

}