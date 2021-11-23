package com.springproject.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class CustomerServiceVO {
	
	private int qnaNo;
	
	private String nickname;
	
	private String qnaTitle;
	
	private String qnaContent;
	
	private Date qnaRegDate;

}
