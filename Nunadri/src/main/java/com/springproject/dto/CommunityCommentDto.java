package com.springproject.dto;


import lombok.Data;

@Data
public class CommunityCommentDto {
   String nickname;
   String id;
   String profile;
   int communityCommentNo;
   String communityComment;
   String noticeCategory;
   int noticeNo;
}