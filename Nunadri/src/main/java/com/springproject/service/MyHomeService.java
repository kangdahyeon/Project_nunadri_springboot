package com.springproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springproject.vo.NoticeMyhouseVO;

public interface MyHomeService {
	
	List<HashMap<String, Object>> getMyHomeBoardList(NoticeMyhouseVO vo);

}
