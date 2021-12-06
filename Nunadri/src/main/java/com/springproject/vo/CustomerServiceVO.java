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

	//검색용 필드
	private String searchCondition;
	private String searchKeyword;
	
	private String id;

}
