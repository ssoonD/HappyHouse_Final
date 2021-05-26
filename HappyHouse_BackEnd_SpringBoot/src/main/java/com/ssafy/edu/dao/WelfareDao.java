package com.ssafy.edu.dao;

import java.util.List;

import com.ssafy.edu.dto.WelfareDto;

public interface WelfareDao {
	public WelfareDto searchByGuName(String guname) throws Exception;
	
}
