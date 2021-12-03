package com.springproject.vo;

import lombok.Data;

@Data
public class FileCommunityVO {

	private int fileNo;

	private String noticeCategory;

	private int noticeNo;
	
	private String noticeFileName;
	
	private String noticeFilePath;
	
	private Long noticeFileSize;
	
	private String nickname;
	
}
