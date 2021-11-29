package com.springproject.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.NoticeMyhouseVO;

@Mapper
public interface MyHomeMapper {

	List<HashMap<String, Object>> getMyHomeBoardList(NoticeMyhouseVO vo);
	
}
