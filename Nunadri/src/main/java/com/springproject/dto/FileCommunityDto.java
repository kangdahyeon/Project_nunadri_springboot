package com.springproject.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class FileCommunityDto {

	
	private Long FileNo;
	
	private int houseNo;
	
	private String noticeCategory;
	
	private int noticeNo;
	
	private String noticeFilename;
	
	private String noticeFilePath;
	
	private String noticeFileSize;
	
	private String nickname;
}
