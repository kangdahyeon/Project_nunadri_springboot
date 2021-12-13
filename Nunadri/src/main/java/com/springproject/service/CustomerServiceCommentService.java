package com.springproject.service;

import java.util.List;

import com.springproject.dto.CustomerServiceCommentDto;
import com.springproject.vo.CustomerServiceCommentVO;
import com.springproject.vo.MemberVO;

public interface CustomerServiceCommentService {
	
	void insertCustomerServiceComment(CustomerServiceCommentVO customerCommentInsert);
	
	List<CustomerServiceCommentVO> getCustomerServiceComment(CustomerServiceCommentVO customerCommentList);
	
	void deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete);

}
