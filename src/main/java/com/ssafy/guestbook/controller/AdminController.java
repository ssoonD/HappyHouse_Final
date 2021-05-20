package com.ssafy.guestbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = { "Content-type=application/json" })
//	public List<MemberDto> userList() {
//		return userService.userList();
//	}
//	
//	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = { "Content-type=application/json" })
//	public List<MemberDto> userRegister(@RequestBody MemberDto memberDto) {
//		userService.userRegister(memberDto);
//		return userService.userList();
//	}
//	
//	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET, headers = { "Content-type=application/json" })
//	public MemberDto userInfo(@PathVariable("userid") String userid) {
//		return userService.userInfo(userid);
//	}
//	
//	@RequestMapping(value = "/user", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
//	public List<MemberDto> userModify(@RequestBody MemberDto memberDto) {
//		userService.userModify(memberDto);
//		return userService.userList();
//	}
//	
//	@RequestMapping(value = "/user/{userid}", method = RequestMethod.DELETE, headers = { "Content-type=application/json" })
//	public List<MemberDto> userDelete(@PathVariable("userid") String userid) {
//		userService.userDelete(userid);
//		return userService.userList();
//	}
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userList() {
		List<MemberDto> list = userService.userList();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
//			return new ResponseEntity<List<MemberDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userRegister(@RequestBody MemberDto memberDto) {
		int cnt = userService.userRegister(memberDto);
		if(cnt != 0) {
			List<MemberDto> list = userService.userList();
			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		} else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/user/{userid}")
	public ResponseEntity<MemberDto> userInfo(@PathVariable("userid") String userid) {
		logger.debug("파라미터 : {}", userid);
		MemberDto memberDto = userService.userInfo(userid);
		if(memberDto != null)
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/user")
	public ResponseEntity<List<MemberDto>> userModify(@RequestBody MemberDto memberDto) {
		userService.userModify(memberDto);
		List<MemberDto> list = userService.userList();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{userid}")
	public ResponseEntity<List<MemberDto>> userDelete(@PathVariable("userid") String userid) {
		userService.userDelete(userid);
		List<MemberDto> list = userService.userList();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	
}
