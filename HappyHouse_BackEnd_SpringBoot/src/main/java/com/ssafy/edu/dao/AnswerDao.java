package com.ssafy.edu.dao;

import java.util.List;

import com.ssafy.edu.dto.AnswerDto;

public interface AnswerDao {
	public List<AnswerDto> findAllAnswer(int qidx);
	public AnswerDto findAnswerByIdx(int idx);
	public boolean addAnswer(AnswerDto answer);
	public boolean updateAnswer(AnswerDto answer);
	public boolean deleteAnswer(int idx);
}
