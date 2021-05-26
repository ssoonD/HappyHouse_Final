package com.ssafy.edu.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.AptDealDao;
import com.ssafy.edu.dto.AptDealDto;




@Service
public class AptDealServiceImpl implements AptDealService {
	
	@Autowired
	private AptDealDao aptDealDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<AptDealDto> dealInfo(String aptname) throws Exception {
		return aptDealDao.dealInfo(aptname);
	}
	



}
