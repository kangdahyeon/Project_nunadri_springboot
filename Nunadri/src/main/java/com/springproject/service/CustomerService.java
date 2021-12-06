package com.springproject.service;

import java.util.List;
import java.util.Map;

import com.springproject.vo.Criteria;
import com.springproject.vo.CustomerServiceCommentVO;
import com.springproject.vo.CustomerServiceVO;

public interface CustomerService {
	
	void insertCustomerServiceBoard(CustomerServiceVO customerBoardInsert);
	
	List<CustomerServiceVO> getCustomerServiceBoardList(CustomerServiceVO customerBoardList, Criteria cri);
	
	CustomerServiceVO getCustomerServiceBoard(CustomerServiceVO getCustomerBoard);
	
	void updateCustomerServiceBoard(CustomerServiceVO customerBoardUpdate);
	
	void deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete);
//	void deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete, CustomerServiceCommentVO customerCommentDelete);
//	List<CustomerServiceVO> deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete, CustomerServiceCommentVO customerCommentDelete);

	
	int selectCustomerServiceBoardCount(CustomerServiceVO paging);
	
//	void deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete);

}
