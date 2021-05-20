package com.ssafy.edu.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.AptDao;
import com.ssafy.edu.dto.AptDto;



@Service
public class AptServiceImpl implements AptService {
	
	@Autowired
	private AptDao aptDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<AptDto> searchByDong(String dong) throws Exception {
		return aptDao.searchByDong(dong);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AptDto> searchByAptName(String aptname) throws Exception {
		return aptDao.searchByAptName(aptname);
	}


}
