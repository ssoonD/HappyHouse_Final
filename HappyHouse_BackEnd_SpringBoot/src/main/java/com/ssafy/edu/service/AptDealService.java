package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.dto.AptDealDto;


public interface AptDealService {
	public List<AptDealDto> dealInfo(String aptname) throws Exception;
	
}
