package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.NoticeMyhouseVO;

@Mapper
public interface MyhouseFileMapper {


	void insertMyhouseFileList(FileMyhouseVO fileInsert);

	void deleteMyhouseFileList(FileMyhouseVO fileDelete);

	List<FileMyhouseVO> getMyhouseFileList(NoticeMyhouseVO fileGet);

}


