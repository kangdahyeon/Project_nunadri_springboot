package com.springproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.MyhouseCommentVO;




@Mapper
public interface MyhouseCommentMapper {
   
   void insertMyhouseComment(MyhouseCommentVO commentInsert);
   
   List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO commentList);
   
   void deleteMyhouseComment(MyhouseCommentVO commentDelete);
   
   //소모임 댓글 삭제
   void deleteSmallGroupComment(MyhouseCommentVO smallGroupCommentDelete);
   
   //소모임 참여인원 중복방지
   int updateJoin(MyhouseCommentVO vo);

}