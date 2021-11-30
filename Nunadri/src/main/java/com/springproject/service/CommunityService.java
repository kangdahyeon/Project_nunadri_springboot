package com.springproject.service;

import java.util.List;

import com.springproject.vo.CommunityVO;



public interface CommunityService {
	
	//글 등록
	public void insertCommunity(CommunityVO cvo);
	//글 수정
	public void updateCommunity(CommunityVO cvo);
	
	//글 상세조회
	public CommunityVO getCommunityDetail(CommunityVO cvo);
	
	//글 삭제
	public void deleteCommunity(CommunityVO cvo);
	
	//리스트조회
	public List<CommunityVO> getCommunityList(CommunityVO cvo);
	
	public int getCoummunityNo();
	
}