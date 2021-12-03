package com.springproject.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MyhouseCommentVO {
	
	private String myhouseCategory;
	
	private int myhouseNo;
	
	private int houseNo;
	
	private int myhouseCommentNo;
	
	private String myhouseComment;
	
	private String nickname;
	
	private Date myhouseCommentRegDate;
	

}
