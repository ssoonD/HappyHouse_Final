package com.ssafy.edu.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dto.AnswerDto;


@Service
public class AnswerDaoImpl implements AnswerDao {
	
	private static final Logger logger = LoggerFactory.getLogger(AnswerDaoImpl.class);
	
	private String ns="com.ssafy.edu.dao.AnswerMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<AnswerDto> findAllAnswer(int no){
		return sqlSession.selectList(ns+"findAllAnswer", no);
	}

	@Override
	public boolean addAnswer(AnswerDto answer) {
		sqlSession.insert(ns+"addAnswer", answer);
		return true;
	}
	
	@Override
	public boolean updateAnswer(AnswerDto answer) {
		sqlSession.update(ns+"updateAnswer",answer);
		return true;
	}
	
	@Override
	public boolean deleteAnswer(int no) {
		sqlSession.delete(ns+"deleteAnswer",no);
		return true;
	}
}
