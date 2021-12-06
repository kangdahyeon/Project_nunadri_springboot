package com.springproject.service;

import java.util.List;

import com.springproject.vo.CustomerServiceCommentVO;

public interface CustomerServiceCommentService {
	
	void insertCustomerServiceComment(CustomerServiceCommentVO customerCommentInsert);
	
	List<CustomerServiceCommentVO> getCustomerServiceComment(CustomerServiceCommentVO customerCommentList);
	
	void deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete);

//	void deleteCustomerServiceCommentList(CustomerServiceCommentVO customerCommentDeleteList);
}
