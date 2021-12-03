package com.springproject.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CommunityMapper;
import com.springproject.mapper.MyhouseMapper;
import com.springproject.service.CommunityService;
import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;

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
		FileCommunityVO fvo = new FileCommunityVO();
		List<FileCommunityVO> fileList = communityMapper.getCommunityFileList(fvo);
		
		return communityMapper.getCommunityDetail(communityDetail);
	}

	@Override
	public void deleteCommunity(CommunityVO communityDelete) {
		communityMapper.deleteCommunity(communityDelete);
	}

	@Override
	public List<CommunityVO> getCommunityList(CommunityVO communityList) {
		return communityMapper.getCommunityList(communityList);
	}
	
	@Override
	public int getCoummunityNo() {
		return communityMapper.getCommunityNo();
	}

	@Override
	public void insertCommunityFileList(List<FileCommunityVO> fileList) {
		for(FileCommunityVO fvo : fileList) {
			communityMapper.insertCommunityFileList(fvo);
		}
		
	}

	@Override
	public List<FileCommunityVO> getCommunityFileList(FileCommunityVO fvo) {
		List<FileCommunityVO> fileList = communityMapper.getCommunityFileList(fvo);
		return communityMapper.getCommunityFileList(fvo);
	}

	@Override
	public void deleteFile(FileCommunityVO fvo) {
		communityMapper.deleteFile(fvo);
	}

	@Override
	public void deleteFileList(FileCommunityVO fvo) {
		communityMapper.deleteFileList(fvo);
	}
}
