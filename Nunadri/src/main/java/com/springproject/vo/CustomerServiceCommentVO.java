package com.springproject.vo;


import lombok.Data;

@Data
public class CustomerServiceCommentVO {
	
	private int qnaNo;
	
	private int qnaCommentNo;
	
	private String nickname;
	
	private String qnaComment;
	
	private String id;
	
	//조회수랑 등록시간 필요??
	
}
