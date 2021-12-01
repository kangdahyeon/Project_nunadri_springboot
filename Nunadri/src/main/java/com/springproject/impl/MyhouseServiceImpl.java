package com.springproject.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MyhouseMapper;
import com.springproject.service.MyhouseService;
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
	public List<NoticeMyhouseVO> getMyhouseBoardList(String category) {
		
		return myhouseMapper.getMyhouseBoardList(category);
	}

	@Override
	public NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice) {
		
		return myhouseMapper.getMyhouseBoard(getNotice);
	}

	@Override
	public void deleteBoardSeq(NoticeMyhouseVO noticeDelete) {
		myhouseMapper.deleteBoardSeq(noticeDelete);
	}

}
