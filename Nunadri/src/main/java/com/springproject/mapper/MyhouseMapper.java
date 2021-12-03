package com.springproject.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.NoticeMyhouseVO;



@Mapper
public interface MyhouseMapper {
	
	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);
	
	
	List<NoticeMyhouseVO> getMyhouseBoardList(Map<String,Object> map);
	
	
	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);
	
	
	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);
	
	void hitIncrease(NoticeMyhouseVO hitNotice);
	
	int getHouseNo(String nickname);
	
	void updateMyhouseBoard(NoticeMyhouseVO updateNotice);
	
	int selectMyHouseBoardCount(NoticeMyhouseVO paging);
	
	
}
