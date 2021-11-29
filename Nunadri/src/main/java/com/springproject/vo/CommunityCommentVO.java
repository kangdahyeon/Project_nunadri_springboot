package com.springproject.vo;

import lombok.Data;

@Data
public class CommunityCommentVO {

   private String noticeCategory;
   
   private int noticeNo;
   
   private int communityCommentNo;
   
   private String communityComment;
   
   private String nickname;
}
