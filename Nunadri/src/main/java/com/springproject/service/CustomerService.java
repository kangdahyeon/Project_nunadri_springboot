package com.springproject.service;

import java.util.List;

import com.springproject.vo.Criteria;
import com.springproject.vo.CustomerServiceVO;

public interface CustomerService {
	
	void insertCustomerServiceBoard(CustomerServiceVO customerBoardInsert);
	
	List<CustomerServiceVO> getCustomerServiceBoardList(CustomerServiceVO customerBoardList, Criteria cri);
	
	CustomerServiceVO getCustomerServiceBoard(CustomerServiceVO getCustomerBoard);
	
	void updateCustomerServiceBoard(CustomerServiceVO customerBoardUpdate);
	
	void deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete);

	void deleteCustomerServiceComment(CustomerServiceVO customerCommentDelete);
	
	int selectCustomerServiceBoardCount(CustomerServiceVO paging);
	
	int getqnaNo(String id);

}
