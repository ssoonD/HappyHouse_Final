package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.dto.HospitalDto;


public interface HospitalService {
	public List<HospitalDto> searchByGugun(String gugun) throws Exception;
	
}
