package com.springproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springproject.vo.CustomerServiceVO;
import com.springproject.vo.FileCustomerServiceVO;

@Mapper
public interface CustomerFileMapper {

	void insertCustomerFileList(FileCustomerServiceVO fvo);

	void deleteCustomerFileList(FileCustomerServiceVO fileDelete);

	List<FileCustomerServiceVO> getCustomerFileList(CustomerServiceVO fileGet);
	
	void deleteCustomerFileAll(CustomerServiceVO deleteFileAll);

}
