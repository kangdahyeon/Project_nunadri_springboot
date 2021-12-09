package com.springproject.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NoticeMyhouseVO {

	private String myhouseCategory;

	private int myhouseNo;

	private int houseNo;

	private String nickname;

	private String myhouseTitle;

	private String myhouseContent;

	private String myhousePicture;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date myhouseRegDate;

	private int myhouseHit;

	//소모임
	private Date myhouseDate;
	private String myhouseTime;
	private int peopleMax;
	private int peopleJoin;

	//검색용 필드
	private String searchCondition;
	private String searchKeyword;
	
	
	private String price;
	



}
