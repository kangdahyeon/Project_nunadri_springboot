package com.springproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CustomerServiceVO;

@Mapper
public interface CustomerServiceMapper {
	
	void insertCustomerServiceBoard(CustomerServiceVO customerBoardInsert);

	List<CustomerServiceVO> getCustomerServiceBoardList(Map<String,Object> map);
	
	CustomerServiceVO getCustomerServiceBoard(CustomerServiceVO getCustomerBoard);
	
	void updateCustomerServiceBoard(CustomerServiceVO customerBoardUpdate);

	void deleteCustomerServiceBoard(CustomerServiceVO customerBoardDelete);

	void deleteCustomerServiceComment(CustomerServiceVO customerCommentDelete);

	int selectCustomerServiceBoardCount(CustomerServiceVO paging);
	
	int getqnaNo(String id);
	
}
