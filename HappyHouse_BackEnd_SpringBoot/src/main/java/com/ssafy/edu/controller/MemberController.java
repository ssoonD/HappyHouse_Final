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

import com.ssafy.edu.dto.MemberDto;
import com.ssafy.edu.help.BoolResult;
import com.ssafy.edu.help.NumberResult;
import com.ssafy.edu.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "Happy House")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "모든 회원의 정보를 반환한다.", response = List.class)
	@RequestMapping(value = "/findAllMember", method = RequestMethod.GET)
	public ResponseEntity<List<MemberDto>> findAllMember() throws Exception {
		logger.info("1-------------findAllMember-----------------------------" + new Date());
		List<MemberDto> members = memberService.findAllMember();
		if (members.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MemberDto>>(members, HttpStatus.OK);
	}

	@ApiOperation(value = "회원아이디로 회원의 정보를 찾는다.", response = MemberDto.class)
	@RequestMapping(value = "/findMemberById/{userid}", method = RequestMethod.GET)
	public ResponseEntity<MemberDto> findMemberById(@PathVariable String userid) throws Exception {
		logger.info("1-------------findMemberById-----------------------------" + new Date());
		MemberDto member = memberService.findMemberById(userid);
		if (member == null || member.getUserid().equals("")) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원이름으로 회원의 정보를 찾는다.", response = MemberDto.class)
	@RequestMapping(value = "/findMemberByName/{username}", method = RequestMethod.GET)
	public ResponseEntity<MemberDto> findMemberByName(@PathVariable String username) throws Exception {
		logger.info("1-------------findMemberByName-----------------------------" + new Date());
		MemberDto member = memberService.findMemberByName(username);
		if (member == null || member.getUserid().equals("")) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
	}

	@ApiOperation(value = "모든 회원의 수를 반환한다.", response = NumberResult.class)
	@RequestMapping(value = "/getMembersTotal", method = RequestMethod.GET)
	public ResponseEntity<NumberResult> getMembersTotal() throws Exception {
		logger.info("2-------------getMembersTotal-----------------------------" + new Date());
		int total = memberService.getMembersTotal();
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("getMembersTotal");
		nr.setState("succ");
		if (total <= 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = " 새로운 회원의 정보를 등록한다.", response = BoolResult.class)
   	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
   	public ResponseEntity<BoolResult> addMember(@RequestBody MemberDto member) throws Exception {
   		logger.info("5-------------addMember-----------------------------"+new Date());
   		logger.info("5-------------addMember-----------------------------"+member);
   		boolean check = memberService.addMember(member);
   		BoolResult nr=new BoolResult();
   		nr.setCount(check);
   		nr.setName("addMember");
   		nr.setState("succ");
   		logger.info("5-------------addMember----------check---------------"+check);
   		if (!check) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
   	}
	
	@ApiOperation(value = "회원의 정보를 수정한다.", response = BoolResult.class)
   	@RequestMapping(value = "/updateMember", method = RequestMethod.PUT)
   	public ResponseEntity<BoolResult> updateMember(@RequestBody MemberDto member) throws Exception {
   		logger.info("1-------------updateMember-----------------------------"+new Date());
   		logger.info("1-------------updateMember-----------------------------"+member);
   		boolean check = memberService.updateMember(member);
   		BoolResult nr=new BoolResult();
   		nr.setCount(check);
   		nr.setName("updateMember");
   		nr.setState("succ");
   		if (!check) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
   	}
	
	@ApiOperation(value = " 해당 회원의 정보를 삭제한다.", response = BoolResult.class)
	@RequestMapping(value = "/deleteMember/{userid}", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deleteEmployee3(@PathVariable String userid) throws Exception {
		logger.info("1-------------deleteMember-----------------------------"+new Date());
		logger.info("1-------------deleteMember-----------------------------"+userid);
		
		boolean check = memberService.deleteMember(userid);
		BoolResult nr=new BoolResult();
		nr.setCount(check);
		nr.setName("deleteMember");
		nr.setState("succ");
		if (!check) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

}
