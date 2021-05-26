package com.ssafy.edu.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.QuestionDao;
import com.ssafy.edu.dto.QuestionDto;


@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao questionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> findAllQuestion(){
		return questionDao.findAllQuestion();
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> findAllQuestionByName(String name){
		return questionDao.findAllQuestionByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> findAllQuestionByTitle(String title){
		return questionDao.findAllQuestionByTitle(title);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> findAllQuestionByContent(String content){
		return questionDao.findAllQuestionByContent(content);
	}
	
	@Override
	@Transactional(readOnly = true)
	public QuestionDto findQuestionByIdx(int idx) {
		return questionDao.findQuestionByIdx(idx);
	}
	
	@Override
	@Transactional
	public boolean addQuestion(QuestionDto question) {
		return questionDao.addQuestion(question);
	}
	
	@Override
	@Transactional
	public boolean updateQuestion(QuestionDto question) {
		return questionDao.updateQuestion(question);
	}
	
	@Override
	@Transactional
	public boolean deleteQuestion(int idx) {
		return questionDao.deleteQuestion(idx);
	}
	
	@Override
	@Transactional
	public int getQuestionsTotalByIdx(int idx) {
		return questionDao.getQuestionsTotalByIdx(idx);
	}
}
