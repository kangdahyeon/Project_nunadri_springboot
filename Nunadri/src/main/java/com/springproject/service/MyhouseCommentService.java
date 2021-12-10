package com.springproject.service;

import java.util.List;

import com.springproject.vo.MyhouseCommentVO;


public interface MyhouseCommentService {
   
void insertMyhouseComment(MyhouseCommentVO commentInsert);
   
   List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO commentList);
   
   void deleteMyhouseComment(MyhouseCommentVO commentDelete);
   
   //소모임 댓글 삭제
   void deleteSmallGroupComment(MyhouseCommentVO smallGroupCommentDelete);
   
   //소모임 업데이트시 숫자값 받아오기
   int updateJoin(MyhouseCommentVO vo);

}