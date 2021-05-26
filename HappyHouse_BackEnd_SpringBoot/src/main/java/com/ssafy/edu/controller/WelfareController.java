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

import com.ssafy.edu.dto.WelfareDto;
import com.ssafy.edu.service.WelfareService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/ssafyvue/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="SSAFY", description="Happy House")
public class WelfareController {

	private static final Logger logger = LoggerFactory.getLogger(WelfareController.class);

	@Autowired
	ServletContext servletContext;

	@Autowired
	private WelfareService welfareService;
	

	@ApiOperation(value = "구이름으로 검색한 시설개수를반환.", response = List.class)
   	@RequestMapping(value = "/searchByGuName/{guname}", method = RequestMethod.GET)
   	public ResponseEntity<WelfareDto> searchByGuName(@PathVariable String guname) throws Exception {
   		logger.info("1-------------searchByGuName-----------------------------"+new Date());
   		WelfareDto dto = welfareService.searchByGuName(guname);
   		if (dto==null) {
   			return new ResponseEntity(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<WelfareDto>(dto, HttpStatus.OK);
   	}
	
}
