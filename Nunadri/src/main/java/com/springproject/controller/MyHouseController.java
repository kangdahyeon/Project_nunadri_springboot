package com.springproject.controller;


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
import com.springproject.service.MemberService;
import com.springproject.service.MyhouseCommentService;
import com.springproject.service.MyhouseFileService;
import com.springproject.service.MyhouseService;
import com.springproject.vo.Criteria;
import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.MemberVO;
import com.springproject.vo.MyhouseCommentVO;
import com.springproject.vo.NoticeMyhouseVO;
import com.springproject.vo.PageVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyHouseController {


	private final MemberService memberService;
	private final MyhouseService myhouseService;
	private final MyhouseFileService myhouseFileService;
	private final MyhouseCommentService myhouseCommentService;
	
	static String condition ="";
	   static String keyword="";


	//유저의 닉네임을 model에 저장(변수명 = "nickname")
	@ModelAttribute("nickname")
	public String userNickname(@AuthenticationPrincipal SecurityUser user) {
		return user.getNickname();
	}


	//우리집 메인 
	@GetMapping("/myhome")
	public String myHomeMain(@AuthenticationPrincipal SecurityUser user, Model model) {

		MemberVO member = memberService.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);
		return "view/myhome/myhome_main";
	}


	//게시판(공지사항, 도와주세요, 자유게시판) 목록
	@RequestMapping("/board/{category}")  //
	public String noticeBoard(@PathVariable("category")String category,NoticeMyhouseVO boardList,
			@AuthenticationPrincipal SecurityUser user, Model model, Criteria cri) {
		boardList.setMyhouseCategory(category);
		//
		boardList.setHouseNo(myhouseService.getHouseNo(user.getNickname()));
		
		  //검색값 없을때 기본 값 설정 
        if(boardList.getSearchCondition() == null) {
           boardList.setSearchCondition("MYHOUSE_TITLE");
           }
           if(boardList.getSearchKeyword() == null) {
              boardList.setSearchKeyword("");
           }
           
           //검색, 키워드 값(페이징 처리시 필요)
           condition = boardList.getSearchCondition();
           keyword = boardList.getSearchKeyword();
           
           int total = myhouseService.selectMyHouseBoardCount(boardList);
		

		model.addAttribute("category", category);
		model.addAttribute("boardList", myhouseService.getMyhouseBoardList(boardList, cri));
		model.addAttribute("pageMaker", new PageVO(cri, total));
        model.addAttribute("condition", boardList.getSearchCondition());
        model.addAttribute("keyword", boardList.getSearchKeyword());
		return "view/myhome/boarder/boarder_list";
	}


	//중고 게시판
	@GetMapping("/fleamarket")
	public String fleamarket() {
		return "view/myhome/fleamarket/fleamarket_list";
	}
	
	@GetMapping("/fleamarketInsert")
	public String fleamarketInsert() {
		return "view/myhome/fleamarket/fleamarket_insert";
	}



	//글작성 폼
	@GetMapping("/boardInsert")
	public String boardInsert() {
		return "view/myhome/myhome_boarder_insert";
	}

	//게시판 글 등록
	@PostMapping("/insertMyhouseBoard")
	public String insertMyhouseBoard(NoticeMyhouseVO noticeInsert,
			HttpServletRequest request, MultipartHttpServletRequest mhsr) {
		//
		
		log.info(noticeInsert.toString());
		
		try {
			noticeInsert.setHouseNo(myhouseService.getHouseNo(noticeInsert.getNickname()));
			int myhouseNo = myhouseService.getMyhouseNo(noticeInsert);
			String category = noticeInsert.getMyhouseCategory();
			System.out.println(category);
			FileUtils fileUtils = new FileUtils();
			List<FileMyhouseVO> fileList = fileUtils.parseFileInfo(noticeInsert.getHouseNo(), 
												category, myhouseNo, request, mhsr);
		
		if(!CollectionUtils.isEmpty(fileList)) {
			myhouseFileService.insertMyhouseFileList(fileList);
		}
		myhouseService.insertMyhouseBoard(noticeInsert);
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "redirect:/board/" + noticeInsert.getMyhouseCategory();
	}


	//게시글 상세 페이지
	@GetMapping("/myhouseBoardDetail/{houseNo}/{myhouseCategory}/{myhouseNo}")
	public String myhouseBoardDetail(NoticeMyhouseVO vo, Model model) {

		//조회수 증가 기능
		myhouseService.hitIncrease(vo);
		
		model.addAttribute("getBoard",myhouseService.getMyhouseBoard(vo));
		model.addAttribute("fileList", myhouseFileService.getMyhouseFileList(vo));

		return "view/myhome/myhome_boarder_detail";
	}


	//게시글 삭제 기능
	@GetMapping("/deleteMyhouseBoard/{houseNo}/{myhouseCategory}/{myhouseNo}")
	public String deleteMyhouseBoard(NoticeMyhouseVO vo) {

		myhouseService.deleteBoardSeq(vo);
		//게시글 삭제 시 댓글 목록 삭제
		myhouseService.deleteMyhouseCommentList(vo);

		return "redirect:/board/" + vo.getMyhouseCategory();
	}

	
	@GetMapping("/updateMyhouseBoard/{houseNo}/{myhouseCategory}/{myhouseNo}")
	public String updateMyhouseBoard(NoticeMyhouseVO update, Model model) {

		model.addAttribute("updateBoard",myhouseService.getMyhouseBoard(update));

		return "view/myhome/boarder/boarder_update";
	}
	
	

	@PostMapping("/updateMyhouse")
	public String updateMyhouse(NoticeMyhouseVO updateNotice) {
		myhouseService.updateMyhouseBoard(updateNotice);

		return "redirect:/myhouseBoardDetail/"+ updateNotice.getHouseNo() + "/" + updateNotice.getMyhouseCategory() + "/" +updateNotice.getMyhouseNo();
	}
	
	//소모임 게시판 리스트
	@RequestMapping("/board/s")  //
	public String smallGroup(NoticeMyhouseVO boardList, @AuthenticationPrincipal SecurityUser user, Model model, Criteria cri) {
		String s= "s";
		
		boardList.setMyhouseCategory(s);
		//
		boardList.setHouseNo(myhouseService.getHouseNo(user.getNickname()));
		
		  //검색값 없을때 기본 값 설정 
        if(boardList.getSearchCondition() == null) {
           boardList.setSearchCondition("MYHOUSE_TITLE");
           }
           if(boardList.getSearchKeyword() == null) {
              boardList.setSearchKeyword("");
           }
           
           //검색, 키워드 값(페이징 처리시 필요)
           condition = boardList.getSearchCondition();
           keyword = boardList.getSearchKeyword();
           
           int total = myhouseService.selectMyHouseBoardCount(boardList);
		

		model.addAttribute("category", s);
		model.addAttribute("boardList", myhouseService.getMyhouseBoardList(boardList, cri));
		model.addAttribute("pageMaker", new PageVO(cri, total));
        model.addAttribute("condition", boardList.getSearchCondition());
        model.addAttribute("keyword", boardList.getSearchKeyword());
		return "view/myhome/smallGroup/boarder_smallgroup_list";
	}

	
	//소모임 글 작성 폼
		@GetMapping("/smallGroupInsert")
		public String smallGroupInsert() {
			return "view/myhome/smallGroup/boarde_boarde_smallgroupinsert";
		}
		
	//소모임 상세 페이지
		@GetMapping("/smallGroupDetail/{houseNo}/{myhouseCategory}/{myhouseNo}")
		public String getSmallGroupBoard(NoticeMyhouseVO vo, Model model) {

			//조회수 증가 기능
			myhouseService.hitIncrease(vo);
			
			
	
			model.addAttribute("getBoard",myhouseService.getMyhouseBoard(vo));
			model.addAttribute("fileList", myhouseFileService.getMyhouseFileList(vo));

			return "view/myhome/smallGroup/boarder_smallgroup_detail";
		}	
	
	//소모임 참여
		@PostMapping("/peopleJoin")
				public String peopleJoin(NoticeMyhouseVO vo, MyhouseCommentVO commentVO,
						@AuthenticationPrincipal SecurityUser user, Model model) {
					String joinComment = "참석합니다 ~ ^0^";
					
					if (commentVO.getNickname() == null) {
						commentVO.setNickname(user.getNickname());
					}if(commentVO.getMyhouseComment() == null) {
						commentVO.setMyhouseComment(joinComment);
					}if(commentVO.getSmallGroupJoin() == null) {
						commentVO.setSmallGroupJoin("O");
					}
					
					//소모임 참여 인원 증가 기능
					myhouseService.peopleJoinIncrease(vo);
					
					//참여 댓글 인서트
					myhouseCommentService.insertMyhouseComment(commentVO);
					
		
					model.addAttribute("smallComment",myhouseCommentService.getMyhouseComment(commentVO));
					return "redirect:/smallGroupDetail/"+vo.getHouseNo()+"/s/"+vo.getMyhouseNo();
				}			
	
		//소모임참여 취소	
		@PostMapping("/deletePeopleJoin")
		public String deletepeopleJoin(NoticeMyhouseVO vo, MyhouseCommentVO commentVO,
				@AuthenticationPrincipal SecurityUser user, Model model) {

			if (commentVO.getNickname() == null) {
				commentVO.setNickname(user.getNickname());
			}if(commentVO.getSmallGroupJoin() == null) {
				commentVO.setSmallGroupJoin("O");
			}
			
//			소모임 참여 인원 감소 기능
			myhouseService.peopleJoinDecrease(vo);
			myhouseCommentService.deleteSmallGroupComment(commentVO);
			
			model.addAttribute("smallComment",myhouseCommentService.getMyhouseComment(commentVO));

			return "redirect:/smallGroupDetail/"+vo.getHouseNo()+"/s/"+vo.getMyhouseNo();
		}			


}