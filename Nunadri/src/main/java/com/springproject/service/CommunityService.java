package com.springproject.service;

import java.util.List;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.Criteria;

public interface CommunityService {
	
	//글 등록
	void insertCommunity(CommunityVO cvo);
	//글 수정
	void updateCommunity(CommunityVO cvo);
//	CommunityVO getCommunity(CommunityVO cvo);
	
	//글 상세조회
	CommunityVO getCommunityDetail(CommunityVO cvo);
	//글 삭제
	void deleteCommunity(CommunityVO cvo);
	
	//리스트조회
	List<CommunityVO> getCommunityList(CommunityVO cvo, Criteria cri);
	
	// 조회수
	void hitIncrease(CommunityVO cvo);
	
	int getCoummunityNo();
	
	int selectCommunityCount(CommunityVO paging);
}