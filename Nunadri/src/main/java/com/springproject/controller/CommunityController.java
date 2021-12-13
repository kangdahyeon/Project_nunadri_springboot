package com.springproject.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.common.FileUtils;
import com.springproject.service.CommunityCommentService;
import com.springproject.service.CommunityFileService;
import com.springproject.service.CommunityService;
import com.springproject.service.MemberService;
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
	private final CommunityService communityService;
	private final CommunityFileService communityFileService;
	private final CommunityCommentService communityCommentService;

	static String condition ="";
	static String keyword="";

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
	@PostMapping(value="/insertCommunity")
	public String insertCommunity(CommunityVO communityInsert,
			HttpServletRequest request, MultipartHttpServletRequest mhsr) {
		try {

			int seq = communityService.getCoummunityNo();
			String category = communityInsert.getNoticeCategory();

			FileUtils fileUtils = new FileUtils();
			List<FileCommunityVO> fileList = fileUtils.parseFileInfo(seq, category, request, mhsr);

			if(!CollectionUtils.isEmpty(fileList)) {
				communityFileService.insertCommunityFileList(fileList);
			}

			communityService.insertCommunity(communityInsert);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return "redirect:/commu/" + communityInsert.getNoticeCategory();
	}

	// 게시물 리스트
	@RequestMapping("/commu/{category}")
	public String communityMain(@AuthenticationPrincipal SecurityUser user,
			@PathVariable("category")String category, CommunityVO communityList, Model model, Criteria cri, LikeVO like) {

		MemberVO member = memberService.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);

		communityList.setNoticeCategory(category);

		if(communityList.getSearchCondition() == null) {
			communityList.setSearchCondition("NOTICE_TITLE");
		}
		if(communityList.getSearchKeyword() == null) {
			communityList.setSearchKeyword("");
		}
		 //검색, 키워드 값(페이징 처리시 필요)
        condition = communityList.getSearchCondition();
        keyword = communityList.getSearchKeyword();

        int total = communityService.selectCommunityCount(communityList);

		model.addAttribute("category", category);
		model.addAttribute("communityList", communityService.getCommunityList(communityList, cri));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		model.addAttribute("condition",communityList.getSearchCondition());
		model.addAttribute("keyword",communityList.getSearchKeyword());


		if(category.equals("B")) {

			model.addAttribute("imgFileList", communityFileService.getCommunityImgList(communityList));
			System.out.println(communityFileService.getCommunityImgList(communityList)+"11111111111111111111111111111111");
			return "view/community/feed/feed_list";

		}

		return "view/community/communityList";
	}

	// 게시물 삭제
	@GetMapping("/deleteCommunity/{noticeCategory}/{noticeNo}")
	public String deleteCommunity(CommunityVO cvo, HttpServletRequest request) {
		
		communityService.deleteCommunity(cvo);
		// 게시물
		communityService.deleteCommunityCommentList(cvo);

		FileCommunityVO fvo = new FileCommunityVO();
		String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";
		List<FileCommunityVO> list = communityFileService.getCommunityFileList(cvo);
		for(FileCommunityVO fvo2 : list) {
			File file = new File(path + fvo2.getCommunityImgUrl());
			if(file.exists()) {
				file.delete();
			}
		}
		

		communityFileService.deleteCommunityFileAll(cvo);


		return "redirect:/commu/" + cvo.getNoticeCategory();
	}

	@GetMapping("/updateCommunityBoard/{noticeCategory}/{noticeNo}")
	public String updateMyhouseBoard(CommunityVO cvo, Model model) {

		model.addAttribute("updateCommunity", communityService.getCommunityDetail(cvo));
		model.addAttribute("fileList", communityFileService.getCommunityFileList(cvo));
		

		if(cvo.getNoticeCategory().equals("B")) {
			System.out.println(cvo.getNoticeCategory());
			return "view/community/feed/feed_update";
		}

		return "view/community/community_boarder_update";
	}


	@PostMapping("/updateCommunity")
	public String updateMyhouse(CommunityVO cvo, @RequestParam("arrNo") int[]arr,
			HttpServletRequest request, MultipartHttpServletRequest mhsr) throws Exception {

		int noticeNo = cvo.getNoticeNo();
		String category = cvo.getNoticeCategory();

		communityService.updateCommunity(cvo);

		//파일삭제를 위한 객체
		FileCommunityVO fvo = new FileCommunityVO();
		String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";

		if(arr != null) {
			fvo.setNoticeCategory(cvo.getNoticeCategory());
			fvo.setNoticeNo(cvo.getNoticeNo());
//			List<FileCommunityVO> list = communityFileService.getCommunityFileList(cvo);
			for(int x : arr) {
//				for(FileCommunityVO fvo2 : list) {
//					File file = new File(path + fvo2.getCommunityImgUrl());
//					if(file.exists()) {
//						file.delete();
//					}
				fvo.setFileNo(x);
				communityFileService.deleteCommunityFile(fvo);
				}
			}
//		}
		
		// 파일업로드
		try {
			FileUtils fileUtils = new FileUtils();
			List<FileCommunityVO> fileList = fileUtils.parseFileInfo(noticeNo, category, request, mhsr);

			if(!CollectionUtils.isEmpty(fileList)) {
				communityFileService.insertCommunityFileList(fileList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/communityDetail/"+ category + "/" + noticeNo;
	}


	// 게시글 상세페이지
	@GetMapping(value="/communityDetail/{noticeCategory}/{noticeNo}")
	public String getCommunityDetail(CommunityVO cvo ,Model model) {

		// 조회수 증가
		communityService.hitIncrease(cvo);

		model.addAttribute("getCommunityDetail", communityService.getCommunityDetail(cvo));
		model.addAttribute("fileList", communityFileService.getCommunityFileList(cvo));
//		System.out.println("파일테스트-----"+communityFileService.getCommunityFileList(cvo));

//		System.out.println(cvo.getNoticeCategory());
		if(cvo.getNoticeCategory().equals("B")) {
//			System.out.println(cvo.getNoticeCategory());

			return "view/community/feed/feed_detail";

		}


		return "view/community/community_boarder_detail";
	}
}
