package com.springproject.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.service.CommunityService;
import com.springproject.vo.CommunityVO;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest(
	properties = {
			"testnickname = 컨트롤러테스트",
			"testId = controllerTest"
			}
)
@Transactional
@AutoConfigureMockMvc
@Slf4j
public class ControllerTests {
	
	@Value("${testId}")
	private String testId;
	
	@Value("${testName}")
	private String testName;
	
	
	@Autowired
	CommunityVO cvo;
	
	@Autowired
	private MockMvc mvc; 
	
	@MockBean
	private CommunityService communityService;
	
	@Test
	public void getInsert() throws Exception {
		cvo.setNickname("컨트롤러테스트");
		cvo.setNoticeCategory("1");
		cvo.setNoticeTitle("컨트롤러 =============================");
		cvo.setNoticeContent("내용내용내용내용");
		
		
	}
	
	@Test
	public void postInsert() throws Exception {
		
	}
}
