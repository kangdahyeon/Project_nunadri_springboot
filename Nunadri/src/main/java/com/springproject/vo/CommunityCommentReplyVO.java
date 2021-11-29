package com.springproject.vo;

import lombok.Data;

@Data
public class CommunityCommentReplyVO {
   private String noticeCategory;
   
   private int noticeNo;
   
   private int communityCommentNo;
   
   private int communityCommentReplyNo;
   
   private String communityReplyComment;
   
   private String nickname;

}
