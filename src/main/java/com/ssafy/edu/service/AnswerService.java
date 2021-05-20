package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.dto.AnswerDto;


public interface AnswerService {
	public List<AnswerDto> findAllAnswer(int no);
	public boolean addAnswer(AnswerDto answer);
	public boolean updateAnswer(AnswerDto answer);
	public boolean deleteAnswer(int no);
}
