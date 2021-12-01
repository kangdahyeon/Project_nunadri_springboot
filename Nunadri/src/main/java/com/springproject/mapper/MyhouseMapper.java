package com.springproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.NoticeMyhouseVO;



@Mapper
public interface MyhouseMapper {
	
	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);
	
	
	List<NoticeMyhouseVO> getMyhouseBoardList(String category);
	
	
	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);
	
	
	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);
	
	
}
