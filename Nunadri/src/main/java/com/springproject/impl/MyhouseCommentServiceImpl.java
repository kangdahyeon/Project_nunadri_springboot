package com.springproject.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MyhouseCommentMapper;
import com.springproject.service.MyhouseCommentService;
import com.springproject.vo.MyhouseCommentVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MyhouseCommentServiceImpl implements MyhouseCommentService {

   private final MyhouseCommentMapper myhouseCommentMapper;

   @Override
   public void insertMyhouseComment(MyhouseCommentVO commentInsert) {
      myhouseCommentMapper.insertMyhouseComment(commentInsert);
   }

	
	  @Override 
	  public List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO
	  commentList) { return myhouseCommentMapper.getMyhouseComment(commentList); }
	 
   
	/*
	 * @Override public List<HashMap<String, Object>>
	 * getMyhouseComment(MyhouseCommentVO commentList) { return
	 * myhouseCommentMapper.getMyhouseComment(commentList); }
	 */

   @Override
   public void deleteMyhouseComment(MyhouseCommentVO commentDelete) {
      myhouseCommentMapper.deleteMyhouseComment(commentDelete);
   }

   //소모임 댓글 삭제
   @Override
	public void deleteSmallGroupComment(MyhouseCommentVO smallGroupCommentDelete) {
	  myhouseCommentMapper.deleteSmallGroupComment(smallGroupCommentDelete);
   }

   //소모임 참여인원 중복방지
   @Override
   public int updateJoin(MyhouseCommentVO vo) {
	   if(myhouseCommentMapper.updateJoin(vo) == 0) {
		   return 0;
	   }else {
		   return myhouseCommentMapper.updateJoin(vo);
	   }
	   
   }

}