package com.springproject.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springproject.dto.MailDto;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SendEmailService{

	//메일 발송 클래스
    private JavaMailSender mailSender;
//    private static final String FROM_ADDRESS = "본인의 이메일 주소를 입력하세요!";


    //이메일 발송
    public void sendMail(MailDto mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress()); //주소 설정
//        message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle()); // 메일 제목
        message.setText(mail.getMessage()); // 메일 내용

        mailSender.send(message);
    }


}
