//package com.springproject.board;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.CollectionUtils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.springproject.mapper.CommunityMapper;
//import com.springproject.vo.CommunityVO;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//class MapperTests {
//	
//	//static final Logger logger = LoggerFactory.getLogger(MapperTests.class);
//	
//	@Autowired
//	private CommunityMapper communityMapper;
//
//
//	@Test
//	public void testOfinsert() {
//		CommunityVO cvo = new CommunityVO();
//
//		cvo.setNoticeTitle("카테고리1");
//		cvo.setNoticeCategory("1");
//		cvo.setNoticeContent("내용내용내용");
//		cvo.setNickname("1-2번");
//		
//		int result = communityMapper.insertCommunity(cvo);
//		log.info("인서트확인용: {}", result);
//	}
//	
//	@Test
//	public void testOfSelectDetail() {
//	
//		CommunityVO cvo = new CommunityVO();
//		cvo.setNoticeCategory("2");
//		cvo.setNoticeNo(3);
//		cvo = communityMapper.getCommunityDetail(cvo);
//		log.info("상세 확인용: {}, {}", cvo.getNickname(), cvo.getNoticeTitle());
//	}
//	
//	@Test
//	public void testOfUpdate() {
//		CommunityVO cvo = new CommunityVO();
//		cvo.setNoticeNo(3);
//		cvo.setNoticeCategory("2");
//		cvo.setNoticeTitle("2-3번 수정");
//		cvo.setNoticeContent("2-3수정하기.");
//		cvo.setNickname("2-3수정");
//
//		int result = communityMapper.updateCommunity(cvo);
//		
//			try {
//				String boardJson = new ObjectMapper().writeValueAsString(cvo);
//
//				log.info("업데이트확인 : {}", boardJson);
////				System.out.println(boardJson);
//				
//
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//	}
//	
//	@Test
//	public void testOfDelete() {
//		CommunityVO cvo = new CommunityVO();
//		cvo.setNoticeCategory("2");
//		cvo.setNoticeNo(3);
//		
//		
//		int result = communityMapper.deleteCommunity(cvo);
//		
//		log.info("삭제 확인용 {}", cvo);
//	}
//	
//	@Test
//	public void testSelectList() {
//		CommunityVO cvo = new CommunityVO();
//		cvo.setNoticeCategory("2");
//		
////		int boardTotalCount = communityMapper.getCommunityTotalCount(cvo);
//		
////		if(boardTotalCount > 0) {
//			List<CommunityVO> communityList = communityMapper.getCommunityList(cvo);
//			for(CommunityVO list :communityList) {
//				log.info("리스트 확인용 {},{},{}",list.getNoticeCategory(),list.getNoticeTitle(), list.getNoticeContent());
//			}
////		}	
//	}
//}
