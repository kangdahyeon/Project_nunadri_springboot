package com.springproject.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CommunityFileMapper;
import com.springproject.service.CommunityFileService;
import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityFileServiceImpl implements CommunityFileService {
	
	private final CommunityFileMapper communityFileMapper;

	@Override
	public void insertCommunityFileList(List<FileCommunityVO> fileList) {
		for(FileCommunityVO fvo : fileList) {
			communityFileMapper.insertCommunityFileList(fvo);
		}
	}

	@Override
	public List<FileCommunityVO> getCommunityFileList(CommunityVO cvo) {
		return communityFileMapper.getCommunityFileList(cvo);
	}


	@Override
	public void deleteFileList(FileCommunityVO fvo) {
		communityFileMapper.deleteFileList(fvo);
	}

}
