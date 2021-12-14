package com.springproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MyhouseCommentDto {
private String myhouseCategory;
	
	private int myhouseNo;
	
	private int houseNo;
	
	private int myhouseCommentNo;
	
	private String id;
	
	private String myhouseComment;
	
	private String nickname;
	
	private Date myhouseCommentRegDate;
	
	private String smallGroupJoin;

}
