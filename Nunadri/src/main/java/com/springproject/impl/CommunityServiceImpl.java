package com.springproject.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CommunityMapper;
import com.springproject.service.CommunityService;
import com.springproject.vo.CommunityVO;
import com.springproject.vo.Criteria;
import com.springproject.vo.LikeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {
	
	private final CommunityMapper communityMapper;

	@Override
	public void insertCommunity(CommunityVO communityInsert) {
		communityMapper.insertCommunity(communityInsert);
	}

	@Override
	public void updateCommunity(CommunityVO communityUpdate) {
		communityMapper.updateCommunity(communityUpdate);
	}
	
	@Override
	public CommunityVO getCommunityDetail(CommunityVO communityDetail) {
	
		return communityMapper.getCommunityDetail(communityDetail);
	}
	
	@Override
	public void hitIncrease(CommunityVO cvo) {
		communityMapper.hitIncrease(cvo);
	}

	@Override
	public void deleteCommunity(CommunityVO communityDelete) {
		communityMapper.deleteCommunityCommentList(communityDelete);
	}

	@Override
	public List<CommunityVO> getCommunityList(CommunityVO communityList, Criteria cri) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("communityList", communityList);
	      cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
	      paramMap.put("criteria", cri);
	      
		return communityMapper.getCommunityList(paramMap);
	}
	
	@Override
	public int getCoummunityNo() {
		return communityMapper.getCommunityNo();
	}

	@Override
	public int selectCommunityCount(CommunityVO paging) {
		return communityMapper.selectCommunityCount(paging);
	}

	@Override
	public void likeCheck(LikeVO like) {
			communityMapper.likeCheck(like);
	}

	@Override
	public void likeCheckCancel(LikeVO like) {
			communityMapper.likeCheckCancel(like);
	}
}
