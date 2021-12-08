package com.springproject.service;

import java.util.List;

import com.springproject.vo.Criteria;
import com.springproject.vo.NoticeMyhouseVO;

public interface MyhouseService {

	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);

	List<NoticeMyhouseVO> getMyhouseBoardList(NoticeMyhouseVO boardList, Criteria cri);
	
	List<NoticeMyhouseVO> memberMyhouseBoardList(NoticeMyhouseVO myhouseBoardList, Criteria cri);

	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);


	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);

	void deleteMyhouseCommentList(NoticeMyhouseVO deleteComment);
	
	void hitIncrease(NoticeMyhouseVO hitNotice);

	int getHouseNo(String nickname);

	void updateMyhouseBoard(NoticeMyhouseVO updateNotice);

	int selectMyHouseBoardCount(NoticeMyhouseVO paging);
	
	int getMyhouseNo(NoticeMyhouseVO getSeq);
	
	


}
