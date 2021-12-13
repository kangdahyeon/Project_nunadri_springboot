package com.springproject.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CommunityFileMapper;
import com.springproject.service.CommunityFileService;
import com.springproject.vo.CommunityVO;
import com.springproject.vo.Criteria;
import com.springproject.vo.FileCommunityVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityFileServiceImpl implements CommunityFileService {

   private final CommunityFileMapper communityFileMapper;

   @Override
   public void insertCommunityFileList(List<FileCommunityVO> fileList) {
      for(FileCommunityVO fvo : fileList) {
         communityFileMapper.insertCommunityFileList(fvo);
      }
   }

   @Override
   public List<FileCommunityVO> getCommunityFileList(CommunityVO cvo) {
      return communityFileMapper.getCommunityFileList(cvo);
   }

   @Override
   public List<FileCommunityVO> getCommunityImgList(CommunityVO cvo, Criteria cri) {
      
      Map<String, Object> paramMap = new HashMap<String, Object>();
         paramMap.put("imgFileList", cvo);
         cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
         paramMap.put("criteria", cri);
      
      return communityFileMapper.getCommunityImgList(paramMap);
   }


   @Override
   public void deleteCommunityFile(FileCommunityVO fvo) {
      communityFileMapper.deleteCommunityFile(fvo);
   }

   @Override
   public void deleteCommunityFileAll(CommunityVO deleteAll) {

      communityFileMapper.deleteCommunityFileAll(deleteAll);
   }

   @Override
   public void deleteFileList(FileCommunityVO fvo) {
      communityFileMapper.deleteFileList(fvo);
   }
}