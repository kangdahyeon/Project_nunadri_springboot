package com.springproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.MyhouseCommentVO;




@Mapper
public interface MyhouseCommentMapper {
   
   void insertMyhouseComment(MyhouseCommentVO commentInsert);
   
   List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO commentList);
   
   void deleteMyhouseComment(MyhouseCommentVO commentDelete);
   
}