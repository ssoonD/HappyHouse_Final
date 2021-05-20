package com.ssafy.edu.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dto.QuestionDto;

@Service
public class QuestionDaoImpl implements QuestionDao {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionDaoImpl.class);
	
	private String ns="com.ssafy.edu.dao.QuestionMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<QuestionDto> findAllQuestion(){
		return sqlSession.selectList(ns+"findAllQuestion");
	}

	@Override
	public List<QuestionDto> findAllQuestionByName(String name){
		return sqlSession.selectList(ns+"findAllQuestionByName", name);
	}
	
	@Override
	public List<QuestionDto> findAllQuestionByTitle(String title){
		return sqlSession.selectList(ns+"findAllQuestionByTitle", title);
	}
	
	@Override
	public List<QuestionDto> findAllQuestionByContent(String content){
		return sqlSession.selectList(ns+"findAllQuestionByContent", content);
	}
	
	@Override
	public boolean addQuestion(QuestionDto question) {
		sqlSession.insert(ns+"addQuestion", question);
		return true;
	}
	
	@Override
	public boolean updateQuestion(QuestionDto question) {
		sqlSession.update(ns+"updateQuestion",question);
		return true;
	}
	
	@Override
	public boolean deleteQuestion(int no) {
		sqlSession.delete(ns+"deleteQuestion",no);
		return true;
	}
}
