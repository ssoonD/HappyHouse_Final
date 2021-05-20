package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.dto.QuestionDto;



public interface QuestionService {
	public List<QuestionDto> findAllQuestion();
	public List<QuestionDto> findAllQuestionByName(String name);
	public List<QuestionDto> findAllQuestionByTitle(String title);
	public List<QuestionDto> findAllQuestionByContent(String content);
	public boolean addQuestion(QuestionDto question);
	public boolean updateQuestion(QuestionDto question);
	public boolean deleteQuestion(int no);
}
