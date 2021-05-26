package com.ssafy.edu.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.HospitalDao;
import com.ssafy.edu.dto.HospitalDto;




@Service
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private HospitalDao hospitalDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<HospitalDto> searchByGugun(String gugun) throws Exception {
		return hospitalDao.searchByGugun(gugun);
	}
	


}
