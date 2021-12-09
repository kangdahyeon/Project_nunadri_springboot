package com.springproject.service;

import java.util.List;

import com.springproject.vo.CustomerServiceVO;
import com.springproject.vo.FileCustomerServiceVO;

public interface CustomerFileService {
	void insertCustomerFileList(List<FileCustomerServiceVO> fileInsert);

	void deleteCustomerFileList(FileCustomerServiceVO fileDelete);

	List<FileCustomerServiceVO> getCustomerFileList(CustomerServiceVO fileGet);
	
	void deleteCustomerFileAll(CustomerServiceVO deleteFileAll);

}
