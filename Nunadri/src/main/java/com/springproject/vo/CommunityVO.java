package com.springproject.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommunityVO {

	private String noticeCategory;
	
	private int noticeNo;
	
	private String id;
	
	private String nickname;

	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date noticeRegDate;
	
	private String noticeTitle;
	
	private String noticeContent;
	
	private int noticeHit;
	
	private String noticePicture;
	
	private String SearchCondition;
	private String SearchKeyword;
}
