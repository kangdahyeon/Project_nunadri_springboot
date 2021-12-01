package com.springproject.service;

import java.util.List;

import com.springproject.vo.NoticeMyhouseVO;

public interface MyhouseService {
	
	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);
	
	List<NoticeMyhouseVO> getMyhouseBoardList(String category);
	
	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);
	
	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);
	

}
