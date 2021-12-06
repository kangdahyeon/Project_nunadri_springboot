package com.springproject.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.FileMyhouseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
	public List<FileCommunityVO> parseFileInfo(int seq, String category, HttpServletRequest request, 
			MultipartHttpServletRequest mhsr) throws IOException {
		
		log.info("fileUtils---->" + mhsr.toString());
		if(ObjectUtils.isEmpty(mhsr)) {
			return null;
		}
		
		List<FileCommunityVO> fileList = new ArrayList<FileCommunityVO>();
		
		//서버의 절대 경로 얻기
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "/upload/";
		
		//위 경로의 폴더가 없으면 폴더 생성
		File file = new File(root_path + attach_path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		//파일 이름들을 iterator로 담음
		Iterator<String> iterator = mhsr.getFileNames();
		while(iterator.hasNext()) {
			
			//파일명으로 파일 리스트 꺼내오기
			List<MultipartFile> list = mhsr.getFiles(iterator.next());
			log.info("list.size() -->" + list.size());
			log.info("seq --->" + seq);
			//파일 리스트 개수 만큼 리턴할 파일 리스트에 담아주고 생성
			for(MultipartFile mf : list) {
				if(mf.getSize() > 0) {
					FileCommunityVO boardFile = new FileCommunityVO();
					boardFile.setNoticeNo(seq);
					boardFile.setNoticeCategory(category);
					boardFile.setNoticeFileSize(mf.getSize());
					boardFile.setNoticeFileName(mf.getOriginalFilename());
					boardFile.setNoticeFilePath(root_path + attach_path); 
					fileList.add(boardFile);
					
					file = new File(root_path + attach_path + mf.getOriginalFilename());
					mf.transferTo(file);
				} else {
					fileList = null;
				}
			}
		}
		return fileList;
	}
	
	
	
	public List<FileMyhouseVO> parseFileInfo(int houseNo, String category, int seq, HttpServletRequest request, 
			MultipartHttpServletRequest mhsr) throws IOException {
		
		log.info("fileUtils---->" + mhsr.toString());
		if(ObjectUtils.isEmpty(mhsr)) {
			return null;
		}
		
		List<FileMyhouseVO> fileList = new ArrayList<FileMyhouseVO>();
		
		//서버의 절대 경로 얻기
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "/upload/";
		
		//위 경로의 폴더가 없으면 폴더 생성
		File file = new File(root_path + attach_path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		//파일 이름들을 iterator로 담음
		Iterator<String> iterator = mhsr.getFileNames();
		while(iterator.hasNext()) {
			
			//파일명으로 파일 리스트 꺼내오기
			List<MultipartFile> list = mhsr.getFiles(iterator.next());
			log.info("list.size() -->" + list.size());
			log.info("seq --->" + seq);
			//파일 리스트 개수 만큼 리턴할 파일 리스트에 담아주고 생성
			for(MultipartFile mf : list) {
				if(mf.getSize() > 0) {
					FileMyhouseVO boardFile = new FileMyhouseVO();
					boardFile.setMyhouseNo(seq);
					boardFile.setMyhouseCategory(category);
					boardFile.setHouseNo(houseNo);
					boardFile.setMyhouseFileSize(mf.getSize());
					boardFile.setMyhouseFilename(mf.getOriginalFilename());
					boardFile.setMyhouseFilePath(root_path + attach_path); 
					fileList.add(boardFile);
					
					file = new File(root_path + attach_path + mf.getOriginalFilename());
					mf.transferTo(file);
				} else {
					fileList = null;
				}
			}
		}
		return fileList;
	}
}
