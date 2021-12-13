package com.springproject.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.mapper.CommunityCommentMapper;
import com.springproject.service.CommunityCommentService;
import com.springproject.vo.CommunityCommentVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityCommentServiceImpl implements CommunityCommentService {
	
	private final CommunityCommentMapper communityCommentMapper;

	@Override
	public void insertCommunityComment(CommunityCommentVO commentInsert) {
		communityCommentMapper.insertCommunityComment(commentInsert);
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
	public List<CommunityCommentDto> getProfile(Map<String, Object> paramMap) {
		
		return communityCommentMapper.getProfile(paramMap);
	}
}
