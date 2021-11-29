package com.springproject.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.mapper.MemberMapper;
import com.springproject.mapper.MyHomeMapper;
import com.springproject.service.MyHomeService;
import com.springproject.vo.MemberVO;
import com.springproject.vo.NoticeMyhouseVO;

@Service
public class MyHomeServiceImpl implements MyHomeService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MyHomeMapper myHomeMapper;

	@Override
	public List<HashMap<String, Object>> getMyHomeBoardList(NoticeMyhouseVO vo){
		List<HashMap<String, Object>> myhouse = myHomeMapper.getMyHomeBoardList(vo);
		
		return myhouse;
	}
}
