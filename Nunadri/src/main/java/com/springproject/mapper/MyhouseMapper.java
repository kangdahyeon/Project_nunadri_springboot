package com.springproject.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.NoticeMyhouseVO;



@Mapper
public interface MyhouseMapper {
	
	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);
	
	
	List<NoticeMyhouseVO> getMyhouseBoardList(Map<String,Object> map);
	

	List<NoticeMyhouseVO> memberMyhouseBoardList(Map<String,Object> map);
	

	int selectMyHouseBoardCount(NoticeMyhouseVO paging);

	
	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);
	
	
	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);
	
	void deleteMyhouseCommentList(NoticeMyhouseVO deleteComment);
	
	
	
	void hitIncrease(NoticeMyhouseVO hitNotice);
	
	int getHouseNo(String nickname);
	
	void updateMyhouseBoard(NoticeMyhouseVO updateNotice);
	
	
	int getMyhouseNo(NoticeMyhouseVO getSeq);

	//소모임 참여인원 증가
	void peopleJoinIncrease(NoticeMyhouseVO peopleJoin);
	
	//소모임 참여인원 감소
	void peopleJoinDecrease(NoticeMyhouseVO peopleDecrease);
	

	
}
