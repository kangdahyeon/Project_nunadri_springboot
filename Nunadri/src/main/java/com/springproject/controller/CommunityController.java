package com.springproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.common.FileUtils;
import com.springproject.service.CommunityCommentService;
import com.springproject.service.CommunityFileService;
import com.springproject.service.CommunityService;
import com.springproject.service.MemberService;
import com.springproject.service.MyhouseService;
import com.springproject.vo.CommunityVO;
import com.springproject.vo.Criteria;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.LikeVO;
import com.springproject.vo.MemberVO;
import com.springproject.vo.PageVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommunityController {

   private final MemberService memberService;
   private final MyhouseService myhouseService;
   private final CommunityService communityService;
   private final CommunityFileService communityFileService;
   private final CommunityCommentService communityCommentService;

   static String condition = "";
   static String keyword = "";

   @ModelAttribute("nickname")
   public String userNickname(@AuthenticationPrincipal SecurityUser user) {
      return user.getNickname();
   }

   @GetMapping("/insertCommu")
   public String commuinsert() {
      return "view/community/community_boarder_insert";
   }

   @GetMapping("/insertFeed")
   public String insertFeed() {
      return "view/community/feed/feed_insert";
   }

   // 게시글 등록
   @PostMapping(value = "/insertCommunity")
   public String insertCommunity(CommunityVO communityInsert, HttpServletRequest request,
         MultipartHttpServletRequest mhsr) {
      try {

         int seq = communityService.getCoummunityNo();
         String category = communityInsert.getNoticeCategory();

         FileUtils fileUtils = new FileUtils();
         List<FileCommunityVO> fileList = fileUtils.parseFileInfo(seq, category, request, mhsr);

         if (!CollectionUtils.isEmpty(fileList)) {
            communityFileService.insertCommunityFileList(fileList);
         }

         communityService.insertCommunity(communityInsert);

      } catch (Exception e) {
         e.printStackTrace();
      }

      return "redirect:/commu/" + communityInsert.getNoticeCategory();
   }

   // 게시물 리스트
   @RequestMapping("/commu/{category}")
   public String communityMain(@AuthenticationPrincipal SecurityUser user, @PathVariable("category") String category,
         CommunityVO communityList, Model model, Criteria cri) {

      MemberVO member = memberService.getMemberInfo(user.getId());
      model.addAttribute("memberInfo", member);

      communityList.setNoticeCategory(category);

      if (communityList.getSearchCondition() == null) {
         communityList.setSearchCondition("NOTICE_TITLE");
      }
      if (communityList.getSearchKeyword() == null) {
         communityList.setSearchKeyword("");
      }
      // 검색, 키워드 값(페이징 처리시 필요)
      condition = communityList.getSearchCondition();
      keyword = communityList.getSearchKeyword();

      int total = communityService.selectCommunityCount(communityList);

      model.addAttribute("category", category);
      model.addAttribute("communityList", communityService.getCommunityList(communityList, cri));
      model.addAttribute("pageMaker", new PageVO(cri, total));
      model.addAttribute("condition", communityList.getSearchCondition());
      model.addAttribute("keyword", communityList.getSearchKeyword());

      if (category.equals("B")) {

         model.addAttribute("imgFileList", communityFileService.getCommunityImgList(communityList, cri));
         System.out.println(communityFileService.getCommunityImgList(communityList, cri).size());
         return "view/community/feed/feed_list";

      }

      return "view/community/communityList";
   }

   
     @PostMapping(value="/commu/B")
     @ResponseBody public String communityMain1(@RequestParam Map<String, Object> parameters, 
     @AuthenticationPrincipal SecurityUser user, Criteria cri, CommunityVO communityList) 
                    throws JsonMappingException, JsonProcessingException {
     
     ObjectMapper mapper = new ObjectMapper(); HashMap<String, Object> hashMap =
     new HashMap<String, Object>();
     
     List<FileCommunityVO> feedList = communityFileService.getCommunityImgList(communityList, cri);
     int total = communityService.selectCommunityCount(communityList);
     
     hashMap.put("pageMaker", new PageVO(cri, total)); 
     hashMap.put("feedList", feedList); 
     String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap);
     
     System.out.println("1111111111111111111======================================="+feedList);
     System.out.println("222222222222222222222===================================================="+json); 
     
     return json; 
     }
    
   // 게시물 삭제
   @GetMapping("/deleteCommunity/{noticeCategory}/{noticeNo}")
   public String deleteCommunity(CommunityVO cvo) {

      communityService.deleteCommunity(cvo);
      // 게시물
      communityService.deleteCommunityCommentList(cvo);



      communityFileService.deleteCommunityFileAll(cvo);

      return "redirect:/commu/" + cvo.getNoticeCategory();
   }

   @GetMapping("/updateCommunityBoard/{noticeCategory}/{noticeNo}")
   public String updateMyhouseBoard(CommunityVO cvo, Model model) {

      model.addAttribute("updateCommunity", communityService.getCommunityDetail(cvo));
      model.addAttribute("fileList", communityFileService.getCommunityFileList(cvo));
      System.out.println(cvo.getNoticeCategory() + "333333333333333333333333333333333333");

      if (cvo.getNoticeCategory().equals("B")) {
         System.out.println(cvo.getNoticeCategory() + "11111111111111111111111111111");
         return "view/community/feed/feed_update";
      }
      System.out.println(cvo.getNoticeCategory() + "222222222222222222222222222222222");

      return "view/community/community_boarder_update";
   }

   @PostMapping("/updateCommunity")
   public String updateMyhouse(CommunityVO cvo, @RequestParam("arrNo") int[] arr, HttpServletRequest request,
         MultipartHttpServletRequest mhsr) throws Exception {

      int noticeNo = cvo.getNoticeNo();
      String category = cvo.getNoticeCategory();

      communityService.updateCommunity(cvo);

      // 파일삭제를 위한 객체
      FileCommunityVO fvo = new FileCommunityVO();

      if (arr != null) {
         fvo.setNoticeCategory(cvo.getNoticeCategory());
         fvo.setNoticeNo(cvo.getNoticeNo());
         for (int x : arr) {
            fvo.setFileNo(x);
            communityFileService.deleteCommunityFile(fvo);
         }
      }
      // 파일업로드
      try {
         FileUtils fileUtils = new FileUtils();
         List<FileCommunityVO> fileList = fileUtils.parseFileInfo(noticeNo, category, request, mhsr);

         if (!CollectionUtils.isEmpty(fileList)) {
            communityFileService.insertCommunityFileList(fileList);
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      if (cvo.getNoticeCategory().equals("B")) {
         System.out.println(cvo.getNoticeCategory());

         return "view/community/feed/feed_detail" + category + "/" + noticeNo;

      }

      return "redirect:/communityDetail/" + category + "/" + noticeNo;
   }

   // 게시글 상세페이지
   @GetMapping(value = "/communityDetail/{noticeCategory}/{noticeNo}")
   public String getCommunityDetail(@AuthenticationPrincipal SecurityUser user, CommunityVO cvo, Model model,
         LikeVO like) {
      // 조회수 증가
      communityService.hitIncrease(cvo);

      model.addAttribute("getCommunityDetail", communityService.getCommunityDetail(cvo));
      model.addAttribute("fileList", communityFileService.getCommunityFileList(cvo));
      System.out.println("파일테스트-----" + communityFileService.getCommunityFileList(cvo));

      if (cvo.getNoticeCategory().equals("B")) {

         like.setId(user.getId());
         LikeVO likeList = communityService.getLikeList(like);
         int likeHit = communityService.likeHit(like);
         model.addAttribute("likeHit", likeHit);

         model.addAttribute("likeList", likeList);

         return "view/community/feed/feed_detail";

      }

      return "view/community/community_boarder_detail";
   }

   @RequestMapping(value = "/communityDetail/{noticeCategory}/{noticeNo}")
   @ResponseBody
   public String like1(@RequestParam Map<String, Object> parameters, @PathVariable("noticeCategory") String category,
         @AuthenticationPrincipal SecurityUser user, Model model, LikeVO like)
         throws JsonMappingException, JsonProcessingException {

      System.out.println(like + "+++++++++++====================");
      ObjectMapper mapper = new ObjectMapper();
      HashMap<String, Object> hashMap = new HashMap<String, Object>();

      LikeVO likeList = communityService.getLikeList(like);
      System.out.println(likeList + "1111111111111111111111111111111111111111111111");

      if (likeList == null) {
         communityService.insertLike(like);
      } else {
         communityService.deleteLike(like);
      }

      LikeVO likeList2 = communityService.getLikeList(like);

      int likeHit = communityService.likeHit(like);
      hashMap.put("likeHit", likeHit);
      hashMap.put("likeList", likeList);
      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap);
      System.out.println(likeList2 + "22222222222222222222222222222222222222222222222");
      System.out.println(likeHit);
      System.out.println(json);
      return json;
   }

 
}