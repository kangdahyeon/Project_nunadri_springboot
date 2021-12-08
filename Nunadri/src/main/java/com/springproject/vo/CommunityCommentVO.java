package com.springproject.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class CommunityCommentVO {

	private String noticeCategory;
	
	private int noticeNo;
	
	private int communityCommentNo;
	
	private String communityComment;
	
	private String nickname;
	
	private Date communityCommentRegdate;
}
