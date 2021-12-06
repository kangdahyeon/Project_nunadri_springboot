package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CustomerServiceCommentVO;

@Mapper
public interface CustomerServiceCommentMapper {
	
	void insertCustomerServiceComment(CustomerServiceCommentVO customerCommentInsert);
	
	List<CustomerServiceCommentVO> getCustomerServiceComment(CustomerServiceCommentVO customerCommentList);
	
	void deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete);

//	void deleteCustomerServiceCommentList(CustomerServiceCommentVO customerCommentDeleteList);
	

}
