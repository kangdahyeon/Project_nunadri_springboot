package com.springproject.vo;

import lombok.Data;

@Data
public class FileCommunityVO {

	private int fileNo;

	private String noticeCategory;
	
	private String id;

	private int noticeNo;
	
	private String noticeFileName;
	
	private String noticeFilePath;
	
	private long noticeFileSize;
	
	private String nickname;
	
	private String communityImgUrl;
}
