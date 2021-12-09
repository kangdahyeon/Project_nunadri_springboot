package com.springproject.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.mapper.CustomerFileMapper;
import com.springproject.service.CustomerFileService;
import com.springproject.vo.CustomerServiceVO;
import com.springproject.vo.FileCustomerServiceVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerFileServiceImpl implements CustomerFileService {
	
	private final CustomerFileMapper customerFileMapper;

	@Override
	public void insertCustomerFileList(List<FileCustomerServiceVO> fileInsert) {
		for(FileCustomerServiceVO fvo : fileInsert) {
			customerFileMapper.insertCustomerFileList(fvo);
		}
	}

	@Override
	public void deleteCustomerFileList(FileCustomerServiceVO fileDelete) {
		customerFileMapper.deleteCustomerFileList(fileDelete);
	}

	@Override
	public List<FileCustomerServiceVO> getCustomerFileList(CustomerServiceVO fileGet) {
		return customerFileMapper.getCustomerFileList(fileGet);
	}

	@Override
	public void deleteCustomerFileAll(CustomerServiceVO deleteFileAll) {
		customerFileMapper.deleteCustomerFileAll(deleteFileAll);
	}

}
