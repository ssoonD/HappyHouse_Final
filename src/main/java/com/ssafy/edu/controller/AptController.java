package com.ssafy.edu.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.edu.dto.AptDto;
import com.ssafy.edu.service.AptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/ssafyvue/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="SSAFY", description="Happy House")
public class AptController {

	private static final Logger logger = LoggerFactory.getLogger(AptController.class);

	@Autowired
	ServletContext servletContext;

	@Autowired
	private AptService aptService;

	@ApiOperation(value = "동으로 검색한 모든 집 정보를 반환한다.", response = List.class)
   	@RequestMapping(value = "/searchByDong/{dong}", method = RequestMethod.GET)
   	public ResponseEntity<List<AptDto>> searchByDong(@PathVariable String dong) throws Exception {
   		logger.info("1-------------searchByDong-----------------------------"+new Date());
   		List<AptDto> apt = aptService.searchByDong(dong);
   		if (apt.isEmpty()) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<List<AptDto>>(apt, HttpStatus.OK);
   	}
	
	@ApiOperation(value = "아파트이름으로 검색한 모든 집 정보를 반환한다.", response = List.class)
   	@RequestMapping(value = "/searchByAptName/{aptname}", method = RequestMethod.GET)
   	public ResponseEntity<List<AptDto>> searchByAptName(@PathVariable String aptname) throws Exception {
   		logger.info("1-------------searchByAptName-----------------------------"+new Date());
   		List<AptDto> apt = aptService.searchByAptName(aptname);
   		if (apt.isEmpty()) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<List<AptDto>>(apt, HttpStatus.OK);
   	}

	
}
