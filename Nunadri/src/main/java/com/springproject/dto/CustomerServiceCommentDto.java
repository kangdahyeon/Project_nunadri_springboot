package com.springproject.dto;

import lombok.Data;

@Data
public class CustomerServiceCommentDto {
	private int qnaNo;
	
	private int qnaCommentNo;
	
	private String nickname;
	
	private String qnaComment;
	
	private String id;
}
