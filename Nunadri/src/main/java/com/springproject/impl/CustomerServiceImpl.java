package com.springproject.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CustomerServiceMapper;
import com.springproject.service.CustomerService;
import com.springproject.vo.Criteria;
import com.springproject.vo.CustomerServiceVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerServiceMapper customerServiceMapper;
	
	@Override
	public void insertCustomerServiceBoard(CustomerServiceVO customerBoardInsert) {
		customerServiceMapper.insertCustomerServiceBoard(customerBoardInsert);
	}
	
	@Override
	public List<CustomerServiceVO> getCustomerServiceBoardList(CustomerServiceVO customerBoardList, Criteria cri) {
	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerBoardList", customerBoardList);
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		paramMap.put("criteria", cri);
		
		return customerServiceMapper.getCustomerServiceBoardList(paramMap);
	}

	@Override
	public void updateCustomerServiceBoard(CustomerServiceVO customerBoardUpdate) {
		customerServiceMapper.updateCustomerServiceBoard(customerBoardUpdate);
	}


	@Override
	public void deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete) {
		 customerServiceMapper.deleteCustomerServiceBoard(customerBoardDelete);
	}


	@Override
	public CustomerServiceVO getCustomerServiceBoard(CustomerServiceVO getCustomerBoard) {
		return customerServiceMapper.getCustomerServiceBoard(getCustomerBoard);
	}

	@Override
	public int selectCustomerServiceBoardCount(CustomerServiceVO paging) {
		return customerServiceMapper.selectCustomerServiceBoardCount(paging);
	}


	@Override
	public void deleteCustomerServiceComment(CustomerServiceVO customerCommentDelete) {
		customerServiceMapper.deleteCustomerServiceComment(customerCommentDelete);
	}

	@Override
	public int getqnaNo(String id) {
		return customerServiceMapper.getqnaNo(id);
	}


	

}
