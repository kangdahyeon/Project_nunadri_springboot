package com.springproject.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springproject.mapper.CommunityCommentMapper;
import com.springproject.service.CommunityCommentService;
import com.springproject.vo.CommunityCommentVO;

@Service
public class CommunityCommentServiceImpl implements CommunityCommentService {
	
	private static CommunityCommentMapper communityCommentMapper;

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
}
