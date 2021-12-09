package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.NoticeMyhouseVO;

@Mapper
public interface CommunityFileMapper {
	
		void insertCommunityFileList(FileCommunityVO fvo);
		
		List<FileCommunityVO> getCommunityFileList(CommunityVO cvo);
		
		List<FileCommunityVO> getCommunityImgList(CommunityVO cvo);
		
		void deleteFileList(FileCommunityVO fvo);
}
