package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.dto.CustomerServiceCommentDto;
import com.springproject.vo.CustomerServiceCommentVO;
import com.springproject.vo.MemberVO;

@Mapper
public interface CustomerServiceCommentMapper {
	
	void insertCustomerServiceComment(CustomerServiceCommentVO customerCommentInsert);
	
	List<CustomerServiceCommentVO> getCustomerServiceComment(CustomerServiceCommentVO customerCommentList);
	
	void deleteCustomerServiceComment(CustomerServiceCommentVO customerCommentDelete);


}
