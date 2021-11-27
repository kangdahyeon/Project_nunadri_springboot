package com.springproject.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CommunityVO {

	private String noticeCategory;
	
	private int noticeNo;
	
	private String nickname;
	
	private Date noticeRegDate;
	
	private String noticeTitle;
	
	private String noticeContent;
	
	private int noticeHit;
	
	private String noticePicture;
	
	private String SearchCondition;
	
	private String SearchKeyword;
	
}
