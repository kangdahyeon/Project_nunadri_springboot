package com.springproject.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CommunityCommentMapper;
import com.springproject.service.CommunityCommentService;
import com.springproject.vo.CommunityCommentVO;
import com.springproject.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityCommentServiceImpl implements CommunityCommentService {
	
	private final CommunityCommentMapper communityCommentMapper;

	@Override
	public void insertCommunityComment(CommunityCommentVO ccv) {
		communityCommentMapper.insertCommunityComment(ccv);
	}

	@Override
	public List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv) {
		return communityCommentMapper.getCommunityComment(ccv);
	}

	@Override
	public void deleteCommunityComment(CommunityCommentVO ccv) {
		communityCommentMapper.deleteCommunityComment(ccv);
	}
	
	@Override
	public Object[] getProfile(MemberVO profile) {
		
		return communityCommentMapper.getProfile(profile);
	}
}
