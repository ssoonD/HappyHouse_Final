package com.ssafy.edu.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.AnswerDao;
import com.ssafy.edu.dto.AnswerDto;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerDao answerDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AnswerDto> findAllAnswer(int qidx){
		return answerDao.findAllAnswer(qidx);
	}
	
	@Override
	@Transactional(readOnly = true)
	public AnswerDto findAnswerByIdx(int idx){
		return answerDao.findAnswerByIdx(idx);
	}

	@Override
	@Transactional
	public boolean addAnswer(AnswerDto answer) {
		return answerDao.addAnswer(answer);
	}
	
	@Override
	@Transactional
	public boolean updateAnswer(AnswerDto answer) {
		return answerDao.updateAnswer(answer);
	}
	
	@Override
	@Transactional
	public boolean deleteAnswer(int idx) {
		return answerDao.deleteAnswer(idx);
	}
}
