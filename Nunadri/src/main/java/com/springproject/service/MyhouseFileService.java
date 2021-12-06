package com.springproject.service;

import java.util.List;

import com.springproject.vo.FileMyhouseVO;
import com.springproject.vo.NoticeMyhouseVO;

public interface MyhouseFileService {
	
	void insertMyhouseFileList(List<FileMyhouseVO> fileInsert);

	void deleteMyhouseFileList(FileMyhouseVO fileDelete);

	List<FileMyhouseVO> getMyhouseFileList(NoticeMyhouseVO fileGet);
}
