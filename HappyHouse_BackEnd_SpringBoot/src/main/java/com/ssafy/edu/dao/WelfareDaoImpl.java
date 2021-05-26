package com.ssafy.edu.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dto.WelfareDto;


@Service
public class WelfareDaoImpl implements WelfareDao {
	
	private static final Logger logger = LoggerFactory.getLogger(WelfareDaoImpl.class);
	
	private String ns="com.ssafy.edu.dao.WelfareMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public WelfareDto searchByGuName(String guname) throws Exception {
		return sqlSession.selectOne(ns+"searchByGuName", guname);
	}
	
}
