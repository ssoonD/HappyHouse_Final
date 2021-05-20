package com.ssafy.edu.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dto.AptDto;

@Service
public class AptDaoImpl implements AptDao {
	
	private static final Logger logger = LoggerFactory.getLogger(AptDaoImpl.class);
	
	private String ns="com.ssafy.edu.dao.AptMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AptDto> searchByDong(String dong) throws Exception {
		return sqlSession.selectList(ns+"searchByDong", dong);
	}
	
	@Override
	public List<AptDto> searchByAptName(String aptname) throws Exception {
		return sqlSession.selectList(ns+"searchByAptName", aptname);
	}


}
