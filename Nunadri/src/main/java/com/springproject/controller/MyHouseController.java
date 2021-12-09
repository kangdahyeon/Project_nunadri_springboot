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
import org.springframework.web.bind.annotation.RequestParam;
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
        
        if(category.equals("m")) {
        	model.addAttribute("fleaMarketList", myhouseFileService.getFleamarketList(boardList));

        	return "view/myhome/fleamarket/fleamarket_list";
        }

		return "view/myhome/boarder/boarder_list";
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
		//게시글 삭제 시 이미지 모두 삭제
		myhouseFileService.deleteMyhouseFileAll(vo);
		
		

		return "redirect:/board/" + vo.getMyhouseCategory();
	}

	//수정 페이지
	@GetMapping("/updateMyhouseBoard/{houseNo}/{myhouseCategory}/{myhouseNo}")
	public String updateMyhouseBoard(NoticeMyhouseVO update, Model model) {

		model.addAttribute("updateBoard",myhouseService.getMyhouseBoard(update));
		model.addAttribute("fileList", myhouseFileService.getMyhouseFileList(update));
		return "view/myhome/boarder/boarder_update";
	}
	
	
	//게시글 수정
	@PostMapping("/updateMyhouse")								//뷰에서 삭제할 파일의 넘버를 배열로 받는다
	public String updateMyhouse(NoticeMyhouseVO updateNotice, @RequestParam("arrNo") int[] arr,
			HttpServletRequest request, MultipartHttpServletRequest mhsr) {
		
		
		
		String category = updateNotice.getMyhouseCategory();
		int houseNo = updateNotice.getHouseNo();
		int myhouseNo = updateNotice.getMyhouseNo();
		
		//수정
		myhouseService.updateMyhouseBoard(updateNotice);
		
		
		//파일 삭제를 위한 객체
		FileMyhouseVO vo = new FileMyhouseVO();
		if(arr != null) {
			vo.setHouseNo(updateNotice.getHouseNo());
			vo.setMyhouseCategory(updateNotice.getMyhouseCategory());
			vo.setMyhouseNo(updateNotice.getMyhouseNo());
			for(int x : arr) {
				vo.setFileNo(x);
				myhouseFileService.deleteMyhouseFileList(vo);
			}
		}
		
		//파일 업로드
		try {
			
			FileUtils fileUtils = new FileUtils();
			List<FileMyhouseVO> fileList = fileUtils.parseFileInfo(houseNo, category, myhouseNo, request, mhsr);
		
		if(!CollectionUtils.isEmpty(fileList)) {
			myhouseFileService.insertMyhouseFileList(fileList);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "redirect:/myhouseBoardDetail/"+ houseNo + "/" + category + "/" +myhouseNo;
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
		public String getSmallGroupBoard(NoticeMyhouseVO vo, MyhouseCommentVO commentVO, Model model) {

			//조회수 증가 기능
			myhouseService.hitIncrease(vo);
			
//			model.addAttribute("getBoard",myhouseService.getSmallGroupBoard(vo, commentVO));
//			System.out.println("이게 무엇일까"+myhouseService.getSmallGroupBoard(vo, commentVO));
			model.addAttribute("getBoard",myhouseService.getMyhouseBoard(vo));
			model.addAttribute("fileList", myhouseFileService.getMyhouseFileList(vo));
			model.addAttribute("getComment", myhouseCommentService.getMyhouseComment(commentVO));
			
			System.out.println("과연"+myhouseCommentService.getMyhouseComment(commentVO));
			
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

					//인원 컬럼
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
			System.out.println("삭제가 되는거냐 "+ commentVO);
			model.addAttribute("smallComment",myhouseCommentService.getMyhouseComment(commentVO));

			return "redirect:/smallGroupDetail/"+vo.getHouseNo()+"/s/"+vo.getMyhouseNo();
		}			
		
		
		@GetMapping("/fleamarketInsert")
		public String fleamarketInsert() {
			return "view/myhome/fleaMarket/fleamarket_insert";
		}
		
		
		
		@GetMapping("/items/{myhouseNo}")
		public String fleamarketDetail(@AuthenticationPrincipal SecurityUser user, NoticeMyhouseVO vo, Model model) {
			int houseNo = myhouseService.getHouseNo(user.getNickname());
			vo.setHouseNo(houseNo);
			
			model.addAttribute("itemDetail", myhouseFileService.getItem(vo));
			
			return "view/myhome/fleaMarket/fleamarket_detail";
		}
		
}