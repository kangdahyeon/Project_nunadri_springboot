package com.springproject.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.vo.CommunityCommentVO;


@Mapper
public interface CommunityCommentMapper {
   
   void insertCommunityComment(CommunityCommentVO commentInsert);
   
   List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv);
   
   void deleteCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentDto> getProfile(Map<String, Object> paramMap);
   
}