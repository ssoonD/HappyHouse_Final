package com.ssafy.edu.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.WelfareDao;
import com.ssafy.edu.dto.WelfareDto;




@Service
public class WelfareServiceImpl implements WelfareService {
	
	@Autowired
	private WelfareDao welfareDao;
	

	@Override
	@Transactional(readOnly = true)
	public WelfareDto searchByGuName(String guname) throws Exception {
		return welfareDao.searchByGuName(guname);
	}
	

}
