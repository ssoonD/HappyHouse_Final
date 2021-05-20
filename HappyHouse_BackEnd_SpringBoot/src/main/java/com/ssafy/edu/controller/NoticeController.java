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

import com.ssafy.edu.dto.NoticeDto;
import com.ssafy.edu.help.BoolResult;
import com.ssafy.edu.help.NumberResult;
import com.ssafy.edu.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "Happy House")
public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "모든 공지사항의 정보를 반환한다.", response = List.class)
	@RequestMapping(value = "/findAllNotice", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDto>> findAllNotice() throws Exception {
		logger.info("1-------------findAllNotice-----------------------------" + new Date());
		List<NoticeDto> notices = noticeService.findAllNotice();
		if (notices.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NoticeDto>>(notices, HttpStatus.OK);
	}
	
	@ApiOperation(value = "제목으로 공지사항의 정보를 찾는다.", response = List.class)
	@RequestMapping(value = "/findNoticeByTitle/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDto>> findNoticeByTitle(@PathVariable String title) throws Exception {
		logger.info("1-------------findNoticeByTitle-----------------------------" + new Date());
		List<NoticeDto> notices = noticeService.findNoticeByTitle(title);
		if (notices.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NoticeDto>>(notices, HttpStatus.OK);
	}
	
	@ApiOperation(value = "제목으로 공지사항의 정보를 찾는다.", response = List.class)
	@RequestMapping(value = "/findNoticeByUsername/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDto>> findNoticeByUsername(@PathVariable String username) throws Exception {
		logger.info("1-------------findNoticeByUsername-----------------------------" + new Date());
		List<NoticeDto> notices = noticeService.findNoticeByUsername(username);
		if (notices.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NoticeDto>>(notices, HttpStatus.OK);
	}
	
	@ApiOperation(value = "idx로 공지사항의 정보를 찾는다.", response = NoticeDto.class)
	@RequestMapping(value = "/findNoticeByIdx/{idx}", method = RequestMethod.GET)
	public ResponseEntity<NoticeDto> findNoticeByIdx(@PathVariable int idx) throws Exception {
		logger.info("1-------------findNoticeByIdx-----------------------------" + new Date());
		NoticeDto notice = noticeService.findNoticeByIdx(idx);
		if (notice == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
	}

	@ApiOperation(value = "모든 공지사항의 수를 반환한다.", response = NumberResult.class)
	@RequestMapping(value = "/getNoticesTotal", method = RequestMethod.GET)
	public ResponseEntity<NumberResult> getNoticesTotal() throws Exception {
		logger.info("2-------------getNoticesTotal-----------------------------" + new Date());
		int total = noticeService.getNoticesTotal();
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("getNoticesTotal");
		nr.setState("succ");
		if (total <= 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = " 새로운 공지사항 정보를 등록한다.", response = BoolResult.class)
   	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
   	public ResponseEntity<BoolResult> addNotice(@RequestBody NoticeDto notice) throws Exception {
   		logger.info("5-------------addNotice-----------------------------"+new Date());
   		logger.info("5-------------addNotice-----------------------------"+notice);
   		boolean check = noticeService.addNotice(notice);
   		BoolResult nr=new BoolResult();
   		nr.setCount(check);
   		nr.setName("addNotice");
   		nr.setState("succ");
   		logger.info("5-------------addNotice----------check---------------"+check);
   		if (!check) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
   	}
	
	@ApiOperation(value = "공지사항의 정보를 수정한다.", response = BoolResult.class)
   	@RequestMapping(value = "/updateNotice", method = RequestMethod.PUT)
   	public ResponseEntity<BoolResult> updateNotice(@RequestBody NoticeDto notice) throws Exception {
   		logger.info("1-------------updateNotice-----------------------------"+new Date());
   		logger.info("1-------------updateNotice-----------------------------"+notice);
   		boolean check = noticeService.updateNotice(notice);
   		BoolResult nr=new BoolResult();
   		nr.setCount(check);
   		nr.setName("updateNotice");
   		nr.setState("succ");
   		if (!check) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
   	}
	
	@ApiOperation(value = "공지사항의 정보를 삭제한다.", response = BoolResult.class)
	@RequestMapping(value = "/deleteNotice/{idx}", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deleteNotice(@PathVariable int idx) throws Exception {
		logger.info("1-------------deleteNotice-----------------------------"+new Date());
		logger.info("1-------------deleteNotice-----------------------------"+idx);
		
		boolean check = noticeService.deleteNotice(idx);
		BoolResult nr=new BoolResult();
		nr.setCount(check);
		nr.setName("deleteNotice");
		nr.setState("succ");
		if (!check) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
}
