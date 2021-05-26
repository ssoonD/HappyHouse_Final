package com.ssafy.edu.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dto.AptDealDto;


@Service
public class AptDealDaoImpl implements AptDealDao {
	
	private static final Logger logger = LoggerFactory.getLogger(AptDealDaoImpl.class);
	
	private String ns="com.ssafy.edu.dao.AptMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AptDealDto> dealInfo(String aptname) throws Exception {
		return sqlSession.selectList(ns+"dealInfo", aptname);
	}


}
