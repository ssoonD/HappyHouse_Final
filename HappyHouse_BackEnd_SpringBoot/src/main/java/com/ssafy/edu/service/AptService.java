package com.ssafy.edu.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.edu.dto.AptDto;

public interface AptService {
	public List<AptDto> searchByDong(String dong) throws Exception;
	public List<AptDto> searchByAptName(String aptname) throws Exception;
	public List<AptDto> searchByAll(HashMap hs) throws Exception;
	
}
