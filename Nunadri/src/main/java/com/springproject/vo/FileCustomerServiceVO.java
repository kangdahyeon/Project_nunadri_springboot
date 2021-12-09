package com.springproject.vo;

import lombok.Data;

@Data
public class FileCustomerServiceVO {
	 
	private String id;
	
	private int fileNo;
	    
	private int qnaNo;
	   
	private String qnaFileName;
	   
	private String qnaFilePath;
	   
    private long qnaFileSize;
	   
    private String nickname;
	   	
    private String qnaImgUrl;
	   
}
