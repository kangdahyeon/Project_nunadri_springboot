package com.springproject.service;

import java.util.List;

import com.springproject.vo.CommunityVO;
import com.springproject.vo.Criteria;
import com.springproject.vo.FileCommunityVO;

public interface CommunityFileService {

   void insertCommunityFileList(List<FileCommunityVO> fileList);

   List<FileCommunityVO> getCommunityFileList(CommunityVO cvo);

   List<FileCommunityVO> getCommunityImgList(CommunityVO cvo, Criteria cri);

   void deleteFileList(FileCommunityVO fvo);
   void deleteCommunityFile(FileCommunityVO fvo);

   void deleteCommunityFileAll(CommunityVO deleteAll);

}