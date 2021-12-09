package com.springproject.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.MyhouseFileMapper;
import com.springproject.service.MyhouseFileService;
import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.NoticeMyhouseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MyhouseFileServiceImpl implements MyhouseFileService {

   private final MyhouseFileMapper myhouseFileMapper;

   @Override
   public void insertMyhouseFileList(List<FileMyhouseVO> fileInsert) {
	   for(FileMyhouseVO fvo : fileInsert) {
		   myhouseFileMapper.insertMyhouseFileList(fvo);
		}
   }

   @Override
   public void deleteMyhouseFileList(FileMyhouseVO fileDelete) {
      myhouseFileMapper.deleteMyhouseFileList(fileDelete);
   }

   @Override
   public List<FileMyhouseVO> getMyhouseFileList(NoticeMyhouseVO fileGet) {
      return myhouseFileMapper.getMyhouseFileList(fileGet);
   }

@Override
public void deleteMyhouseFileAll(NoticeMyhouseVO deleteFileAll) {

	myhouseFileMapper.deleteMyhouseFileAll(deleteFileAll);
}



@Override
public List<HashMap<String, Object>> getFleamarketList(NoticeMyhouseVO join) {
	return myhouseFileMapper.getFleamarketList(join);
}

@Override
public List<HashMap<String, Object>> getItem(NoticeMyhouseVO item) {
	return myhouseFileMapper.getItem(item);
}
   
   

}