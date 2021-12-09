package com.springproject.controller;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.common.FileUtils;
import com.springproject.impl.UserDetailsServiceImpl;
import com.springproject.service.MemberService;
import com.springproject.service.MyhouseFileService;
import com.springproject.service.MyhouseService;
import com.springproject.vo.Criteria;
import com.springproject.vo.MemberVO;
import com.springproject.vo.NoticeMyhouseVO;
import com.springproject.vo.PageVO;
import com.springproject.vo.SecurityUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageController {
  
	private final MemberService memberservice;

	private final PasswordEncoder encoder;
	
	private final MyhouseService myhouseService;
	
	private final MyhouseFileService myhouseFileService;

	private final UserDetailsServiceImpl UserDetailsServiceImpl;
	
	static String condition ="";
	static String keyword="";


	//마이페이지 메인
	@GetMapping("/mypage")
	public String myPageMain(@AuthenticationPrincipal SecurityUser user, Model model) {
		MemberVO member = memberservice.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);
		return "view/member/mypage/mypage_main";
	}


	//회원정보 수정 페이지
	@GetMapping("/mypagemodify")
	public String myPage(@AuthenticationPrincipal SecurityUser user, Model model) {
		MemberVO member = memberservice.getMemberInfo(user.getId());
		model.addAttribute("memberInfo", member);
		return "view/member/mypage/member_modify";
	}

	//비밀번호 변경 페이지
	@GetMapping("/changePassword")
	public String changePassword() {
		return "view/member/mypage/change_pw";
	}
	
	//게시판(공지사항, 도와주세요, 자유게시판) 목록
		@GetMapping("/myhouseBoard/{category}")  //
		public String noticeBoard(@PathVariable("category")String category,NoticeMyhouseVO myhouseBoardList,
				@AuthenticationPrincipal SecurityUser user, Model model, Criteria cri) {
			
			System.out.println("================================================================");
			myhouseBoardList.setMyhouseCategory(category);
			//
			myhouseBoardList.setHouseNo(myhouseService.getHouseNo(user.getNickname()));
			
			myhouseBoardList.setNickname(user.getNickname());
			
			  //검색값 없을때 기본 값 설정 
	        if(myhouseBoardList.getSearchCondition() == null) {
	        	myhouseBoardList.setSearchCondition("MYHOUSE_TITLE");
	           }
	           if(myhouseBoardList.getSearchKeyword() == null) {
	        	   myhouseBoardList.setSearchKeyword("");
	           }
	           
	           //검색, 키워드 값(페이징 처리시 필요)
	           condition = myhouseBoardList.getSearchCondition();
	           keyword = myhouseBoardList.getSearchKeyword();
	           
	           int total = myhouseService.selectMyHouseBoardCount(myhouseBoardList);
			
	           System.out.println(category);
			model.addAttribute("category", category);
			model.addAttribute("paramList", myhouseService.memberMyhouseBoardList(myhouseBoardList, cri));
			model.addAttribute("pageMaker", new PageVO(cri, total));
	        model.addAttribute("condition", myhouseBoardList.getSearchCondition());
	        model.addAttribute("keyword", myhouseBoardList.getSearchKeyword());
	       
	        System.out.println(category);
	        System.out.println(myhouseService.memberMyhouseBoardList(myhouseBoardList, cri)+"1111111111111111111111111111111111");
	        return "view/member/mypage/member_myhouse_boarder_list";
		}
		
		/*
		 * @RequestMapping(value="/myhouseBoard/{category}") //
		 * 
		 * @ResponseBody public String noticeBoard1(@PathVariable("category")String
		 * category,NoticeMyhouseVO myhouseBoardList,
		 * 
		 * @AuthenticationPrincipal SecurityUser user, Model model, Criteria cri) {
		 * myhouseBoardList.setMyhouseCategory(category); //
		 * myhouseBoardList.setHouseNo(myhouseService.getHouseNo(user.getNickname()));
		 * 
		 * myhouseBoardList.setNickname(user.getNickname());
		 * 
		 * //검색값 없을때 기본 값 설정 if(myhouseBoardList.getSearchCondition() == null) {
		 * myhouseBoardList.setSearchCondition("MYHOUSE_TITLE"); }
		 * if(myhouseBoardList.getSearchKeyword() == null) {
		 * myhouseBoardList.setSearchKeyword(""); }
		 * 
		 * //검색, 키워드 값(페이징 처리시 필요) condition = myhouseBoardList.getSearchCondition();
		 * keyword = myhouseBoardList.getSearchKeyword();
		 * 
		 * int total = myhouseService.selectMyHouseBoardCount(myhouseBoardList);
		 * 
		 * System.out.println(category); model.addAttribute("category", category);
		 * model.addAttribute("boardList",
		 * myhouseService.getMyhouseBoardList(myhouseBoardList, cri));
		 * model.addAttribute("pageMaker", new PageVO(cri, total));
		 * model.addAttribute("condition", myhouseBoardList.getSearchCondition());
		 * model.addAttribute("keyword", myhouseBoardList.getSearchKeyword());
		 * System.out.println("post"); System.out.println(category);
		 * System.out.println(myhouseService.getMyhouseBoardList(myhouseBoardList,
		 * cri)); return "view/member/mypage/member_myhouse_boarder_list"; }
		 */

		@RequestMapping(value="/myhouseBoard/{category}")  //
		@ResponseBody
		public String noticeBoard1(@RequestParam Map<String, Object> parameters, @PathVariable("category")String category,NoticeMyhouseVO myhouseBoardList,
				@AuthenticationPrincipal SecurityUser user, Model model, Criteria cri) throws JsonMappingException, JsonProcessingException {
			
//			  String json = parameters.get("paramList").toString();
		      ObjectMapper mapper = new ObjectMapper();
		      HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		      List<Map<String, Object>> paramList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
		      
		   
			myhouseBoardList.setMyhouseCategory(category);
			//
			myhouseBoardList.setHouseNo(myhouseService.getHouseNo(user.getNickname()));
			
			myhouseBoardList.setNickname(user.getNickname());
			
			  //검색값 없을때 기본 값 설정 
	        if(myhouseBoardList.getSearchCondition() == null) {
	        	myhouseBoardList.setSearchCondition("MYHOUSE_TITLE");
	           }
	           if(myhouseBoardList.getSearchKeyword() == null) {
	        	   myhouseBoardList.setSearchKeyword("");
	           }
	           
	           //검색, 키워드 값(페이징 처리시 필요)
	           condition = myhouseBoardList.getSearchCondition();
	           keyword = myhouseBoardList.getSearchKeyword();
	           
	           int total = myhouseService.selectMyHouseBoardCount(myhouseBoardList);
			
	           System.out.println(category);
 			model.addAttribute("category", category);
			model.addAttribute("boardList", myhouseService.memberMyhouseBoardList(myhouseBoardList, cri));
			model.addAttribute("pageMaker", new PageVO(cri, total));
	        model.addAttribute("condition", myhouseBoardList.getSearchCondition());
	        model.addAttribute("keyword", myhouseBoardList.getSearchKeyword());
	       System.out.println("post");
	        System.out.println(category);
	        System.out.println(myhouseService.memberMyhouseBoardList(myhouseBoardList, cri));
	        
	        List<NoticeMyhouseVO> paramList = myhouseService.memberMyhouseBoardList(myhouseBoardList, cri);
	         hashMap.put("paramList", paramList);
	         String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap);
	         System.out.println("json String==================" + json);
		
	         
	        return json;
		}
	


	//회원정보 수정
	@PostMapping("/update")
	@ResponseBody
	public String updateMember(@AuthenticationPrincipal SecurityUser user, MemberVO vo, HttpSession session
						) throws IOException {
		
		//		System.out.println("vo.getEmail() : " +  vo.getEmail());
		//		System.out.println( " memberservice.findId(user.getId()).getEmail() : " + memberservice.findId(user.getId()).getEmail());
		//		System.out.println("=================================================");
		//		System.out.println( "vo.getNickname() : " + vo.getNickname());
		//		System.out.println("user.getNickname() : " +user.getNickname());

//		System.out.println(vo.toString());
			

			if(memberservice.findEmail(vo.getEmail()) != null && !vo.getEmail().equals(memberservice.findId(user.getId()).getEmail())) {
				throw new IllegalStateException("이미 가입된 이메일입니다.");
			}else if(memberservice.findNickname(vo.getNickname()) != null && !vo.getNickname().equals(user.getNickname())) {

				throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
			}else {
//				vo.setProfile(profile.toString());
//				memberservice.updateMember(vo, profile);
				System.out.println("정보 업데이트");
				// user에 직접 들어갈 수 있도록 여기서 데이터 입력해줌
				UserDetails userDetails = UserDetailsServiceImpl.loadUserByUsername(vo.getId());

				//세션 등록
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);

				session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);


				return "/mypage";
			}

	}

	//회원 탈퇴
	@GetMapping("/deleteMember")
	public String deleteMember(@AuthenticationPrincipal SecurityUser user) {



		memberservice.deleteMember(user.getId());

		//세션 종료 후 로그아웃
		SecurityContextHolder.clearContext();

		return "redirect:/";
	}

	//회원 탈퇴
	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable("id") String id) {
		
		System.out.println(id + "111111111111111111111111111111111111111111111111");
		memberservice.deleteMember(id);
		System.out.println(id + "222222222222222222222222222222222222222222222222");

		
		return "redirect:/admin";
	}


	//비밀번호 변경
	@PostMapping("/changePassword")
	@ResponseBody
	public String checkPassword(@AuthenticationPrincipal SecurityUser user, String pwd) {


		MemberVO member = new MemberVO();

		member.setId(user.getId());
		pwd = encoder.encode(pwd);
		member.setPwd(pwd);

		memberservice.updatePwd(member);

		return "/mypage";
	}
	
	 @RequestMapping("/admin")
	    public String admin(@AuthenticationPrincipal SecurityUser user,Model model, MemberVO vo, Criteria cri) {
	       
	        //검색값 없을때 기본 값 설정 
	           if(vo.getSearchCondition() == null) {
	        	   vo.setSearchCondition("ID");
	              }
	              if(vo.getSearchKeyword() == null) {
	            	  vo.setSearchKeyword("");
	              } 

	             
	              System.out.println(memberservice.getAdminInfo(vo, cri));
	              System.out.println(vo.getSearchCondition());
	              System.out.println(vo.getSearchKeyword());
	              //검색, 키워드 값(페이징 처리시 필요)
	              condition = vo.getSearchCondition();
	              keyword = vo.getSearchKeyword();
	              
	             int total = memberservice.selectMyHouseMemberCount(vo);
	         
	         model.addAttribute("adminInfo", memberservice.getAdminInfo(vo, cri));
	         model.addAttribute("pageMaker", new PageVO(cri, total));
	         model.addAttribute("condition", vo.getSearchCondition());
	           model.addAttribute("keyword", vo.getSearchKeyword());
	           
	           return "view/admin/admin_member_list";
	    }

	@PostMapping(value="/upload/uploadForm")
	public void uploadForm(@RequestParam("profile") MultipartFile profile, MemberVO vo) throws Exception {
		
		FileUtils utils = new FileUtils();
		log.info("파일이름 {}", profile.getOriginalFilename());
		log.info("파일크기 {}", profile.getSize());
		log.info("컨텐트 타입 {}", profile.getContentType());
		
		String root_path = System.getProperty("user.dir") + "\\src\\main\\webapp\\";
		String attach_path = "\\profile\\";
		String imgPath = "";
		
		File target = new File(root_path + attach_path);
		
		
		imgPath = utils.uploadFile(root_path + attach_path, profile.getOriginalFilename(), profile.getBytes());
		log.info(imgPath);
		vo.setProfile(imgPath.toString());
		
		memberservice.updateProfile(vo);
	}

}