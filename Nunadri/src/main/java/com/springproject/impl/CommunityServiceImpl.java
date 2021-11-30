package com.springproject.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.mapper.CommunityMapper;
import com.springproject.service.CommunityService;
import com.springproject.vo.CommunityVO;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityMapper communityMapper;

	@Override
	public void insertCommunity(CommunityVO cvo) {
		communityMapper.insertCommunity(cvo);
	}

	@Override
	public void updateCommunity(CommunityVO cvo) {
		communityMapper.updateCommunity(cvo);
	}

	@Override
	public CommunityVO getCommunityDetail(CommunityVO cvo) {
		return communityMapper.selectCommunityDetail(cvo);
	}

	@Override
	public void deleteCommunity(CommunityVO cvo) {
		communityMapper.deleteCommunity(cvo);
	}

	@Override
	public List<CommunityVO> getCommunityList(CommunityVO cvo) {
		List<CommunityVO> communotyList = Collections.emptyList();
		
		int CommunityTotalCount = communityMapper.selectCommunityTotalCount(cvo);
		
		if(CommunityTotalCount > 0) {
			communotyList = communityMapper.selectCommunityList(cvo);
		}
		
		return communotyList;
	}
	@Override
	public int getCoummunityNo() {
		return communityMapper.getCommunityNo();
	}
	
}
