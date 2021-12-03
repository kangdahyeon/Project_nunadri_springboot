package com.springproject.service;

import java.util.List;

import com.springproject.vo.NoticeMyhouseVO;

public interface MyhouseService {
	
	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);
	
	List<NoticeMyhouseVO> getMyhouseBoardList(NoticeMyhouseVO boardList);
	
	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);
	
	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);
	

}
