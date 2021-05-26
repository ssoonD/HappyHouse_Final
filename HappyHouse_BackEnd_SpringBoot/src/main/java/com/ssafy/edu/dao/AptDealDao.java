package com.ssafy.edu.dao;

import java.util.List;

import com.ssafy.edu.dto.AptDealDto;


public interface AptDealDao {
	public List<AptDealDto> dealInfo(String aptname) throws Exception;
	
}
