package com.springproject.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CustomerServiceCommentMapper;
import com.springproject.service.CustomerServiceCommentService;
import com.springproject.vo.CustomerServiceCommentVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceCommentImpl implements CustomerServiceCommentService{

	private final CustomerServiceCommentMapper customerServiceCommentMapper;
	
	@Override
	public void insertCustomerServiceComment(CustomerServiceCommentVO customerCommentInsert) {
		customerServiceCommentMapper.insertCustomerServiceComment(customerCommentInsert);
	}

	@Override
	public List<CustomerServiceCommentVO> getCustomerServiceComment(CustomerServiceCommentVO customerCommentList) {
		return customerServiceCommentMapper.getCustomerServiceComment(customerCommentList);
	}

	@Override
	public void deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete) {
		customerServiceCommentMapper.deleteCustomerServiceComment(customerCommentDelete);
	}

//	@Override
//	public void deleteCustomerServiceCommentList(CustomerServiceCommentVO customerCommentDeleteList) {
//		customerServiceCommentMapper.deleteCustomerServiceCommentList(customerCommentDeleteList);		
//	}

}
