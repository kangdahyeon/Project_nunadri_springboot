package com.springproject.service;

import java.util.List;


import com.springproject.vo.MyhouseCommentVO;


public interface MyhouseCommentService {
   
void insertMyhouseComment(MyhouseCommentVO commentInsert);
   
   List<MyhouseCommentVO> getMyhouseComment(MyhouseCommentVO commentList);
   
   void deleteMyhouseComment(MyhouseCommentVO commentDelete);
   

}