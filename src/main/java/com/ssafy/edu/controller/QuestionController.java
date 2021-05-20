package com.ssafy.edu.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.dto.QuestionDto;
import com.ssafy.edu.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssafy.edu.help.BoolResult;

//http://localhost:8197/ssafyvue/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="SSAFY", description="Happy House")
public class QuestionController {
	
	public static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionService questionService;

	 @ApiOperation(value = "모든 질문글의 정보를 반환한다.", response = List.class)
	   	@RequestMapping(value = "/findAllQuestion", method = RequestMethod.GET)
	   	public ResponseEntity<List<QuestionDto>> findAllQuestion() throws Exception {
	   		logger.info("1-------------findAllQuestion-----------------------------"+new Date());
	   		List<QuestionDto> question = questionService.findAllQuestion();
	   		if (question.isEmpty()) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<List<QuestionDto>>(question, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = "게시자이름으로 검색한 질문글의 정보를 반환한다.", response = List.class)
	   	@RequestMapping(value = "/findAllQuestionByName/{name}", method = RequestMethod.GET)
	   	public ResponseEntity<List<QuestionDto>> findAllQuestionByName(@PathVariable String name) throws Exception {
	   		List<QuestionDto> question = questionService.findAllQuestionByName(name);
	   		if (question.isEmpty()) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<List<QuestionDto>>(question, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = "질문제목으로 검색한 질문글의 정보를 반환한다.", response = List.class)
	   	@RequestMapping(value = "/findAllQuestionByTitle/{title}", method = RequestMethod.GET)
	   	public ResponseEntity<List<QuestionDto>> findAllQuestionByTitle(@PathVariable String title) throws Exception {
	   		List<QuestionDto> question = questionService.findAllQuestionByName(title);
	   		if (question.isEmpty()) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<List<QuestionDto>>(question, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = "질문내용으로 검색한 질문글의 정보를 반환한다.", response = List.class)
	   	@RequestMapping(value = "/findAllQuestionByName/{content}", method = RequestMethod.GET)
	   	public ResponseEntity<List<QuestionDto>> findAllQuestionByContent(@PathVariable String content) throws Exception {
	   		List<QuestionDto> question = questionService.findAllQuestionByName(content);
	   		if (question.isEmpty()) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<List<QuestionDto>>(question, HttpStatus.OK);
	   	}
	    
	  
	   
	    
	   
	    @ApiOperation(value = " 질문 작성 ", response = BoolResult.class)
	   	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	   	public ResponseEntity<BoolResult> addQuestion(@RequestBody QuestionDto dto) throws Exception {
	   		boolean total = questionService.addQuestion(dto);
	   		BoolResult nr=new BoolResult();
	   		nr.setCount(total);
	   		nr.setName("addQuestion");
	   		nr.setState("succ");
	   		if (!total) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = " 질문 수정 ", response = BoolResult.class)
	   	@RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
	   	public ResponseEntity<BoolResult> updateQuestion(@RequestBody QuestionDto dto) throws Exception {
	   		boolean total = questionService.updateQuestion(dto);
	   		BoolResult nr=new BoolResult();
	   		nr.setCount(total);
	   		nr.setName("updateQuestion");
	   		nr.setState("succ");
	   		if (!total) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = "게시물 삭제", response = BoolResult.class)
		@RequestMapping(value = "/deleteQuestion/{no}", method = RequestMethod.POST)
		public ResponseEntity<BoolResult> deleteQuestion(@PathVariable int no) throws Exception {
			
			boolean total = questionService.deleteQuestion(no);
			BoolResult nr=new BoolResult();
			nr.setCount(total);
			nr.setName("deleteQuestion");
			nr.setState("succ");
			if (!total) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
		}
	
}
