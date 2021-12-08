package com.springproject.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.springproject.mapper.MyhouseMapper;
import com.springproject.service.MyhouseService;
import com.springproject.vo.Criteria;
import com.springproject.vo.FileCommunityVO;
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
	
	

}
