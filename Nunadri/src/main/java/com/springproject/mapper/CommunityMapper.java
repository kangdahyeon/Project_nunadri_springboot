package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.NoticeMyhouseVO;

@Mapper
public interface CommunityMapper {
	
		//글 생성
		int insertCommunity(CommunityVO communityInsert);
		
		//글 수정
		int updateCommunity(CommunityVO cvo);
		CommunityVO getCommunity(CommunityVO cvo);
		
		//글 삭제
		int deleteCommunity(CommunityVO cvo);
		
		//상세페이지 조회
		CommunityVO getCommunityDetail(CommunityVO cvo);
		
		// 게시물 출력
		List<CommunityVO> getCommunityList(CommunityVO communityList);
		
		// 조회수
		void hitIncrease(CommunityVO hitNotice);
		
		int selectCommunityCount(CommunityVO paging);

		int getCommunityNo();
		
		int insertCommunityFileList(FileCommunityVO fvo);
		
		List<FileCommunityVO> getCommunityFileList(FileCommunityVO fvo);
		
		void deleteFile(FileCommunityVO fvo);
		
		void deleteFileList(FileCommunityVO fvo);
}
