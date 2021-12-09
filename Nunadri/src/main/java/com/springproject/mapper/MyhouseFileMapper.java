package com.springproject.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.NoticeMyhouseVO;

@Mapper
public interface MyhouseFileMapper {


	void insertMyhouseFileList(FileMyhouseVO fileInsert);

	void deleteMyhouseFileList(FileMyhouseVO fileDelete);

	List<FileMyhouseVO> getMyhouseFileList(NoticeMyhouseVO fileGet);
	
	
	void deleteMyhouseFileAll(NoticeMyhouseVO deleteFileAll);
	
	List<HashMap<String, Object>> getFleamarketList(NoticeMyhouseVO join);

	List<HashMap<String, Object>> getItem(NoticeMyhouseVO item);

}


