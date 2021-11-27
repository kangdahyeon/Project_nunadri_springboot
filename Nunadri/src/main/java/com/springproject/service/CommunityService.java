package com.springproject.service;

import java.util.List;

import com.springproject.vo.CommunityVO;



public interface CommunityService {
	
	//글 등록
	public void insertBoard(CommunityVO cvo);
	//글 수정
	public void updateBoard(CommunityVO cvo);

	public CommunityVO getCommunityDetail(CommunityVO cvo);

	public int deleteCommunity(CommunityVO cvo);

	public List<CommunityVO> getCommunityList(CommunityVO cvo);
	
}