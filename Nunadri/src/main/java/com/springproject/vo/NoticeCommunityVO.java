package com.springproject.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeCommunityVO {

	private String noticeCategory;
	
	private int noticeNo;
	
	private String nickname;
	
	private Date noticeRegDate;
	
	private String noticeTitle;
	
	private String noticeContent;
	
	private int noticeHit;
	
	private String noticePicture;
}
