package com.springproject.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.common.FileUtils;
import com.springproject.service.CommunityService;
import com.springproject.service.MemberService;
import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommunityController {
	
	private final MemberService memberService;
	private final CommunityService communityService;
	
	@ModelAttribute("nickname")
	public String userNickname(@AuthenticationPrincipal SecurityUser user) {
		return user.getNickname();
	}
	
	@GetMapping("/insertCommu")
	public String commuinsert() {
		return "view/community/community_insert";
	}
	
	@PostMapping(value="/insertCommunity")
	public String insertCommunity(CommunityVO communityInsert, 
			HttpServletRequest request, MultipartHttpServletRequest mhsr) {
		try {
	
			int seq = communityService.getCoummunityNo();
			String category = communityInsert.getNoticeCategory();
			
			FileUtils fileUtils = new FileUtils();			List<FileCommunityVO> fileList = fileUtils.parseFileInfo(seq, category, request, mhsr);
			
			if(CollectionUtils.isEmpty(fileList) ==false) {
				communityService.insertCommunityFileList(fileList);
				log.info("파일업로드 확인용 {}",fileList);
			}
			
			communityService.insertCommunity(communityInsert);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/commu/" + communityInsert.getNoticeCategory();
	}
	
	@RequestMapping("/commu/{category}")
	public String communityMain(@AuthenticationPrincipal SecurityUser user,
			@PathVariable("category")String category, CommunityVO communityList, Model model) {
		
		MemberVO member = memberService.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);
		
		int seq = communityList.getNoticeNo();
		communityList.setNoticeCategory(category);
		if(communityList.getSearchCondition() == null) {
			communityList.setSearchCondition("NOTICE_TITLE");
		}
		if(communityList.getSearchKeyword() == null) {
			communityList.setSearchKeyword("");
		}
		model.addAttribute("category", category);
		model.addAttribute("communityList", communityService.getCommunityList(communityList));
		return "view/community/communityList";
	}
	
	@RequestMapping(value="/updateCommunity")
	public String updateCommunity(@ModelAttribute("community") CommunityVO cvo, 
			HttpServletRequest request) throws IOException {
		log.info("게시물 수정 : {}, {}, {}, {}, {}, {}"
					,cvo.getNoticeCategory(),cvo.getNoticeNo(), cvo.getNickname()
					,cvo.getNoticeTitle(), cvo.getNoticeContent(), cvo.getNoticeRegDate());
		
		int no = cvo.getNoticeNo();
		
		communityService.updateCommunity(cvo);
		return "redirect:communityList";
	}
	
	@RequestMapping(value="/deleteCommunity/{noticeCategory}/{noticeNo}")
	public String deleteCommunity(CommunityVO cvo) {
		log.info("게시물 삭제 : {}, {}", cvo.getNoticeCategory(), cvo.getNoticeNo());
		communityService.deleteCommunity(cvo);
		return "redirect:communityList";
	}
	
	@GetMapping("/communityDetail/{noticeCategory}/{noticeNo}")
	public String getCommunityDetail(@PathVariable("noticeCategory")String Category,
									@PathVariable("noticeNo") int noticeNo, Model model) {
		log.info("디테일 확인용");
		CommunityVO cvo = new CommunityVO();
		cvo.setNoticeCategory(Category);
		cvo.setNoticeNo(noticeNo);
		
		model.addAttribute("getCommunityDetail", communityService.getCommunityDetail(cvo));
		return "view/community/boarder_detail";
	}
}