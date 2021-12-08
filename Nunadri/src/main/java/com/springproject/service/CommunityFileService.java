package com.springproject.service;

import java.util.List;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;

public interface CommunityFileService {
	
	void insertCommunityFileList(List<FileCommunityVO> fileList);
	
	List<FileCommunityVO> getCommunityFileList(CommunityVO cvo);
	
	void deleteFileList(FileCommunityVO fvo);
	
}