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

import com.ssafy.edu.help.BoolResult;
import com.ssafy.edu.dto.AnswerDto;
import com.ssafy.edu.service.AnswerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/ssafyvue/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="SSAFY", description="Happy House")
public class AnswerController {
	
public static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	
	@Autowired
	private AnswerService answerService;

	 @ApiOperation(value = "해당질문의 모든 답변를 반환한다.", response = List.class)
	   	@RequestMapping(value = "/findAllAnswer/{qidx}", method = RequestMethod.GET)
	   	public ResponseEntity<List<AnswerDto>> findAllAnswer(@PathVariable int qidx) throws Exception {
	   		logger.info("1-------------findAllAnswer-----------------------------"+new Date());
	   		List<AnswerDto> answer = answerService.findAllAnswer(qidx);
	   		if (answer.isEmpty()) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<List<AnswerDto>>(answer, HttpStatus.OK);
	   	}
	 
	 @ApiOperation(value = "답변 검색", response = AnswerDto.class)
	   	@RequestMapping(value = "/findAnswerByIdx/{idx}", method = RequestMethod.GET)
	   	public ResponseEntity<AnswerDto> findAnswerByIdx(@PathVariable int idx) throws Exception {
	   		logger.info("1-------------findAnswerByIdx-----------------------------"+new Date());
	   		AnswerDto answer = answerService.findAnswerByIdx(idx);
	   		if (answer == null) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<AnswerDto>(answer, HttpStatus.OK);
	   	}
	    
	   
	    @ApiOperation(value = "답변 작성 ", response = BoolResult.class)
	   	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	   	public ResponseEntity<BoolResult> addAnswer(@RequestBody AnswerDto dto) throws Exception {
	   		boolean total = answerService.addAnswer(dto);
	   		BoolResult nr=new BoolResult();
	   		nr.setCount(total);
	   		nr.setName("addAnswer");
	   		nr.setState("succ");
	   		if (!total) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = "답변 수정 ", response = BoolResult.class)
	   	@RequestMapping(value = "/updateAnswer", method = RequestMethod.PUT)
	   	public ResponseEntity<BoolResult> updateAnswer(@RequestBody AnswerDto dto) throws Exception {
	   		boolean total = answerService.updateAnswer(dto);
	   		BoolResult nr=new BoolResult();
	   		nr.setCount(total);
	   		nr.setName("updateAnswer");
	   		nr.setState("succ");
	   		if (!total) {
	   			return new ResponseEntity(HttpStatus.NO_CONTENT);
	   		}
	   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	   	}
	    
	    @ApiOperation(value = "답변 삭제", response = BoolResult.class)
		@RequestMapping(value = "/deleteAnswer/{idx}", method = RequestMethod.DELETE)
		public ResponseEntity<BoolResult> deleteAnswer(@PathVariable int idx) throws Exception {
			
			boolean total = answerService.deleteAnswer(idx);
			BoolResult nr=new BoolResult();
			nr.setCount(total);
			nr.setName("deleteAnswer");
			nr.setState("succ");
			if (!total) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
		}
}
