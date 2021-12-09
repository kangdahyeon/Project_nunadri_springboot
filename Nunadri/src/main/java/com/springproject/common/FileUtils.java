package com.springproject.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.vo.FileCommunityVO;
import com.springproject.vo.FileCustomerServiceVO;
import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

  public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
      UUID uuid = UUID.randomUUID(); //서로 다른 개체들을 구별하기 위해서 이름을 부여할 때 사용, 파일명 중복 문제를 해결할 수 있다.
      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
      String savedFileName = uuid.toString() + extension; //UUID로 받은 값과 원래 파일의 이름의 확장자를 조합해서 저장될 파일 이름을 만듦
      String fileUploadFullUrl = uploadPath  + savedFileName;
      FileOutputStream fos = new FileOutputStream(fileUploadFullUrl); //파일이 저장될 위치와 파일의 이름을 넘겨 파일에 쓸 파일 출력 스트림을 생성
      fos.write(fileData); //fileData를 파일 출력 스트림에 입력
      fos.close();
      return savedFileName; 

   } 

   public List<FileMyhouseVO> parseFileInfo(int houseNo, String category, int seq, HttpServletRequest request, 
         MultipartHttpServletRequest mhsr) throws Exception {

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


         String imgName = "";


         //파일 리스트 개수 만큼 리턴할 파일 리스트에 담아주고 생성
         for(MultipartFile mf : list) {
            if(mf.getSize() > 0) {
               FileMyhouseVO boardFile = new FileMyhouseVO();

               //이미지 url
               imgName = this.uploadFile(root_path + attach_path, mf.getOriginalFilename(), mf.getBytes());

               boardFile.setMyhouseNo(seq);
               boardFile.setMyhouseCategory(category);
               boardFile.setHouseNo(houseNo);
               boardFile.setMyhouseFileSize(mf.getSize());
               boardFile.setMyhouseFilename(mf.getOriginalFilename());
               boardFile.setMyhouseFilePath(root_path + attach_path);
               boardFile.setMyhouseImgUrl(imgName);

               fileList.add(boardFile);

               file = new File(imgName);
               mf.transferTo(file);
            } else {
               fileList = null;
            }
         }
      }
      return fileList;
   }


					file = new File(imgName);
					mf.transferTo(file);
				} else {
					fileList = null;
				}
			}
		}
		return fileList;
	}


   public List<FileCommunityVO> parseFileInfo(int seq, String category, HttpServletRequest request, 
         MultipartHttpServletRequest mhsr) throws Exception {

      if(ObjectUtils.isEmpty(mhsr)) {
         return null;
      }
      
      List<FileCommunityVO> fileList = new ArrayList<FileCommunityVO>();
      
      //서버의 절대 경로 얻기

      String root_path = request.getSession().getServletContext().getRealPath("/");
      String attach_path = "/upload/";
     
//      UUID uuid = UUID.randomUUID();
      
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

          String imgName = "";

         //파일 리스트 개수 만큼 리턴할 파일 리스트에 담아주고 생성
         for(MultipartFile mf : list) {
            if(mf.getSize() > 0) {
               FileCommunityVO boardFile = new FileCommunityVO();
               imgName = this.uploadFile(root_path + attach_path, mf.getOriginalFilename(), mf.getBytes());
               boardFile.setNoticeNo(seq);
               boardFile.setNoticeCategory(category);
               boardFile.setNoticeFileSize(mf.getSize());
               boardFile.setNoticeFileName(mf.getOriginalFilename());
               boardFile.setNoticeFilePath(root_path+attach_path);
               boardFile.setCommunityImgUrl(imgName);
               
               fileList.add(boardFile);
            } else {
               fileList = null;
            }
         }
      }
      return fileList;
   }  
   


   public List<MemberVO> parseProfileInfo(HttpServletRequest request, MultipartHttpServletRequest profile
		   , String id) throws Exception {

	      if(ObjectUtils.isEmpty(profile)) {
	         return null;
	      }
	      
	      List<MemberVO> fileList = new ArrayList<MemberVO>();
	      
	      //서버의 절대 경로 얻기

	      String root_path = request.getSession().getServletContext().getRealPath("/");
	      String attach_path = "/profile/";
	      
	      //위 경로의 폴더가 없으면 폴더 생성
	      File file = new File(root_path + attach_path);
	      if(file.exists() == false) {
	         file.mkdirs();
	      }
	      
	      //파일 이름들을 iterator로 담음
	      Iterator<String> iterator = profile.getFileNames();
	      while(iterator.hasNext()) {
	         
	         //파일명으로 파일 리스트 꺼내오기
	    	  List<MultipartFile> list = profile.getFiles(iterator.next());

	          String imgName = "";

	         //파일 리스트 개수 만큼 리턴할 파일 리스트에 담아주고 생성
	         for(MultipartFile mf : list) {
	            if(mf.getSize() > 0) {
	            	MemberVO boardFile = new MemberVO();
	               imgName = this.uploadFile(root_path + attach_path, mf.getOriginalFilename(), mf.getBytes());
	               boardFile.setId(id);
	               boardFile.setProfile(imgName);
	               
	               fileList.add(boardFile);
	            } else {
	               fileList = null;
	            }
	         }
	      }
	      return fileList;
	   }  
   }   

   
   //고객센터 파일
   public List<FileCustomerServiceVO> parseFileInfo(String id, int seq, 
		   HttpServletRequest request, 
			MultipartHttpServletRequest mhsr) throws Exception {

		if(ObjectUtils.isEmpty(mhsr)) {
			return null;
		}

		List<FileCustomerServiceVO> fileList = new ArrayList<FileCustomerServiceVO>();

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


			String imgName = "";


			//파일 리스트 개수 만큼 리턴할 파일 리스트에 담아주고 생성
			for(MultipartFile mf : list) {
				if(mf.getSize() > 0) {
					FileCustomerServiceVO boardFile = new FileCustomerServiceVO();

					//이미지 url
					imgName = this.uploadFile(root_path + attach_path, mf.getOriginalFilename(), mf.getBytes());

					boardFile.setQnaNo(seq);
					boardFile.setId(id);
					boardFile.setQnaFileSize(mf.getSize());
					boardFile.setQnaFileName(mf.getOriginalFilename());
					boardFile.setQnaFilePath(root_path + attach_path);
					boardFile.setQnaImgUrl(imgName);

					fileList.add(boardFile);

					file = new File(imgName);
					mf.transferTo(file);
				} else {
					fileList = null;
				}
			}
		}
		return fileList;
	   
   }
}

