package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;

@Mapper
public interface CommunityMapper {
	
		//글 생성
		int insertCommunity(CommunityVO communityInsert);
		
		//글 수정
		int updateCommunity(CommunityVO cvo);
		
		//글 삭제
		int deleteCommunity(CommunityVO cvo);
		
		//상세페이지 조회
		CommunityVO getCommunityDetail(CommunityVO cvo);
		
		// 게시물 총 갯수
		int getCommunityTotalCount(CommunityVO cvo);
		
		// 게시물 출력
		List<CommunityVO> getCommunityList(CommunityVO communityList);

		int getCommunityNo();
		
		int insertCommunityFileList(FileCommunityVO fvo);
		
		List<FileCommunityVO> getCommunityFileList(FileCommunityVO fvo);
		
		void deleteFile(FileCommunityVO fvo);
		
		void deleteFileList(FileCommunityVO fvo);
}
