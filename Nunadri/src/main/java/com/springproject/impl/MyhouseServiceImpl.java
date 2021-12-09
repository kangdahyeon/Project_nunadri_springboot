package com.springproject.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MyhouseMapper;
import com.springproject.service.MyhouseService;
import com.springproject.vo.Criteria;
import com.springproject.vo.MyhouseCommentVO;
import com.springproject.vo.NoticeMyhouseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MyhouseServiceImpl implements MyhouseService {

	private final MyhouseMapper myhouseMapper;
	
	@Override
	public void insertMyhouseBoard(NoticeMyhouseVO noticeInsert) {
		myhouseMapper.insertMyhouseBoard(noticeInsert);
	}
	
	
	
	
	@Override
	public List<NoticeMyhouseVO> getMyhouseBoardList(NoticeMyhouseVO boardList, Criteria cri) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("boardList", boardList);
	      cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
	      paramMap.put("criteria", cri);
	      
		return myhouseMapper.getMyhouseBoardList(paramMap);
	}
	
	@Override
	public List<NoticeMyhouseVO> memberMyhouseBoardList(NoticeMyhouseVO myhouseBoardList, Criteria cri) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paramList", myhouseBoardList);
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		paramMap.put("criteria", cri);
		
		return myhouseMapper.memberMyhouseBoardList(paramMap);
	}

	@Override
	public NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice) {
		
		return myhouseMapper.getMyhouseBoard(getNotice);
	}

	@Override
	public void deleteBoardSeq(NoticeMyhouseVO noticeDelete) {
		myhouseMapper.deleteBoardSeq(noticeDelete);
	}

	@Override
	public void hitIncrease(NoticeMyhouseVO hitNotice) {
		myhouseMapper.hitIncrease(hitNotice);
		
	}

	@Override
	public int getHouseNo(String nickname) {
		return myhouseMapper.getHouseNo(nickname);
	}

	@Override
	public void updateMyhouseBoard(NoticeMyhouseVO updateNotice) {
		myhouseMapper.updateMyhouseBoard(updateNotice);
		
	}
	
	 @Override
	   public int selectMyHouseBoardCount(NoticeMyhouseVO paging) {
	      return myhouseMapper.selectMyHouseBoardCount(paging);
	   }



	@Override
	public void deleteMyhouseCommentList(NoticeMyhouseVO deleteComment) {
		myhouseMapper.deleteMyhouseCommentList(deleteComment);
		
	}



	@Override
	public int getMyhouseNo(NoticeMyhouseVO getSeq) {
		return myhouseMapper.getMyhouseNo(getSeq);
	}


	//소모임 보드 상세페이지
//	@Override
//	public NoticeMyhouseVO getSmallGroupBoard(NoticeMyhouseVO getSmallGroup) {
//		return myhouseMapper.getSmallGroupBoard(getSmallGroup);
//	}

	@Override
	public List<NoticeMyhouseVO> getSmallGroupBoard(NoticeMyhouseVO getSmallGroup, MyhouseCommentVO commentVO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("getBoard", getSmallGroup);
		paramMap.put("getComment", commentVO);
		return myhouseMapper.getSmallGroupBoard(paramMap);
	}
////	
	//소모임 참여인원 증가
	@Override
	public void peopleJoinIncrease(NoticeMyhouseVO peopleJoin) {
		myhouseMapper.peopleJoinIncrease(peopleJoin);
	}

	//소모임 참여인원 감소
	@Override
	public void peopleJoinDecrease(NoticeMyhouseVO peopleDecrease) {
	    myhouseMapper.peopleJoinDecrease(peopleDecrease);
	}

	//소모임
//	@Override
//	public int updateJoin(NoticeMyhouseVO vo) {
//		return myhouseMapper.updateJoin(vo);
//	}









	
	

}
