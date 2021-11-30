package com.springproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CommunityVO;



@Mapper
public interface CommunityMapper {
	
		//글 생성
		public int insertCommunity(CommunityVO cvo);
		
		//글 수정
		public int updateCommunity(CommunityVO cvo);
		
		//글 삭제
		public int deleteCommunity(CommunityVO cvo);
		
		//상세페이지 조회
		public CommunityVO selectCommunityDetail(CommunityVO cvo);
		
		// 게시물 총 갯수
		public int selectCommunityTotalCount(CommunityVO cvo);
		
		// 게시물 출력
		public List<CommunityVO> selectCommunityList(CommunityVO cvo);

		public int getCommunityNo();
}
