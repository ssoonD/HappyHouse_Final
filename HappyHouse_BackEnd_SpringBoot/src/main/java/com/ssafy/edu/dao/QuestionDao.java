package com.ssafy.edu.dao;

import java.util.List;

import com.ssafy.edu.dto.QuestionDto;

public interface QuestionDao {
	public List<QuestionDto> findAllQuestion();
	public List<QuestionDto> findAllQuestionByName(String name);
	public List<QuestionDto> findAllQuestionByTitle(String title);
	public List<QuestionDto> findAllQuestionByContent(String content);
	public QuestionDto findQuestionByIdx(int idx);
	public boolean addQuestion(QuestionDto question);
	public boolean updateQuestion(QuestionDto question);
	public boolean deleteQuestion(int idx);
	public int getQuestionsTotalByIdx(int idx);
}
