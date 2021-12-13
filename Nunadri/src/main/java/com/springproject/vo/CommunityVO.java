package com.springproject.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class CommunityVO {

	private String noticeCategory;
	
	private int noticeNo;
	
	private String nickname;
	private String id;
	
	private Date noticeRegDate;
	
	private String noticeTitle;
	
	private String noticeContent;
	
	private int noticeHit;
	
	private String noticePicture;
	
	private String SearchCondition;
	private String SearchKeyword;
}
