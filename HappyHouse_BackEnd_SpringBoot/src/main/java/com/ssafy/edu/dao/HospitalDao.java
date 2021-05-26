package com.ssafy.edu.dao;

import java.util.List;

import com.ssafy.edu.dto.HospitalDto;


public interface HospitalDao {
	public List<HospitalDto> searchByGugun(String gugun) throws Exception;
	
}
