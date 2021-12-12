package com.springproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.dto.CommunityCommentDto;
import com.springproject.vo.CommunityCommentVO;
import com.springproject.vo.MemberVO;


@Mapper
public interface CommunityCommentMapper {
   
   void insertCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentVO> getCommunityComment(CommunityCommentVO ccv);
   
   void deleteCommunityComment(CommunityCommentVO ccv);
   
   List<CommunityCommentDto> getProfile(MemberVO profile);
   
}