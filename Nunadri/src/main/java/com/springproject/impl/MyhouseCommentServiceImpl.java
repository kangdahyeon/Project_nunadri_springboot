package com.springproject.impl;

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
   public List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO commentList) {
      return myhouseCommentMapper.getMyhouseComment(commentList);
   }

   @Override
   public void deleteMyhouseComment(MyhouseCommentVO commentDelete) {
      myhouseCommentMapper.deleteMyhouseComment(commentDelete);
   }




}