package com.springproject.service;

import java.util.List;

import com.springproject.vo.Criteria;
import com.springproject.vo.NoticeMyhouseVO;

public interface MyhouseService {

	void insertMyhouseBoard(NoticeMyhouseVO noticeInsert);

	List<NoticeMyhouseVO> getMyhouseBoardList(NoticeMyhouseVO boardList, Criteria cri);

	NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice);


	void deleteBoardSeq(NoticeMyhouseVO noticeDelete);

	void deleteMyhouseCommentList(NoticeMyhouseVO deleteComment);
	
	void hitIncrease(NoticeMyhouseVO hitNotice);

	int getHouseNo(String nickname);

	void updateMyhouseBoard(NoticeMyhouseVO updateNotice);

	int selectMyHouseBoardCount(NoticeMyhouseVO paging);
	
	int getMyhouseNo(NoticeMyhouseVO getSeq);
		
	//소모임 보드 상세페이지
	NoticeMyhouseVO getSmallGroupBoard(NoticeMyhouseVO getSmallGroup);
//	List<NoticeMyhouseVO> getSmallGroupBoard(NoticeMyhouseVO getSmallGroup, MyhouseCommentVO commentVO);

	//소모임 참여인원 증가
	void peopleJoinIncrease(NoticeMyhouseVO peopleJoin);
	
	//소모임 참여인원 감소
	void peopleJoinDecrease(NoticeMyhouseVO peopleDecrease);


}
