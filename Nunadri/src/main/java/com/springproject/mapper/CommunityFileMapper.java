package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.NoticeMyhouseVO;

@Mapper
public interface CommunityFileMapper {
	
		int insertCommunityFileList(FileCommunityVO fvo);
		
		List<FileCommunityVO> getCommunityFileList(FileCommunityVO fvo);
		
		void deleteFile(FileCommunityVO fvo);
		
		void deleteFileList(FileCommunityVO fvo);
}
