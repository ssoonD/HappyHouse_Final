package com.ssafy.edu.dao;

import java.util.List;

import com.ssafy.edu.dto.AptDto;

public interface AptDao {
	public List<AptDto> searchByDong(String dong) throws Exception;
	public List<AptDto> searchByAptName(String aptname) throws Exception;
	
}
