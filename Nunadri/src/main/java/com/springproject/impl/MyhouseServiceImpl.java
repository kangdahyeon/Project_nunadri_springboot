package com.springproject.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.springproject.mapper.MyhouseMapper;
import com.springproject.service.MyhouseService;
import com.springproject.vo.Criteria;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.NoticeMyhouseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MyhouseServiceImpl implements MyhouseService {

	private final MyhouseMapper myhouseMapper;
	
	@Override
	public void insertMyhouseBoard(NoticeMyhouseVO noticeInsert) {
		myhouseMapper.insertMyhouseBoard(noticeInsert);
	}
	
	
	
	public void insertFile(FileCommunityVO vo, MultipartFile file) {
		//저장할 경로 지정
				String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
				
				UUID uuid = UUID.randomUUID();
				
				String fileName = uuid + "_" + file.getOriginalFilename();
				File saveFile = new File(projectPath, fileName);
				
				try {
					file.transferTo(saveFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	
	
	@Override
	public List<NoticeMyhouseVO> getMyhouseBoardList(NoticeMyhouseVO boardList, Criteria cri) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("boardList", boardList);
	      cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
	      paramMap.put("criteria", cri);
	      
		return myhouseMapper.getMyhouseBoardList(paramMap);
	}

	@Override
	public NoticeMyhouseVO getMyhouseBoard(NoticeMyhouseVO getNotice) {
		
		return myhouseMapper.getMyhouseBoard(getNotice);
	}

	@Override
	public void deleteBoardSeq(NoticeMyhouseVO noticeDelete) {
		myhouseMapper.deleteBoardSeq(noticeDelete);
	}

	@Override
	public void hitIncrease(NoticeMyhouseVO hitNotice) {
		myhouseMapper.hitIncrease(hitNotice);
		
	}

	@Override
	public int getHouseNo(String nickname) {
		return myhouseMapper.getHouseNo(nickname);
	}

	@Override
	public void updateMyhouseBoard(NoticeMyhouseVO updateNotice) {
		myhouseMapper.updateMyhouseBoard(updateNotice);
		
	}
	
	 @Override
	   public int selectMyHouseBoardCount(NoticeMyhouseVO paging) {
	      return myhouseMapper.selectMyHouseBoardCount(paging);
	   }
	
	

}
