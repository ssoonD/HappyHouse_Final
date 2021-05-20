package com.ssafy.guestbook;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.service.GuestBookService;

public class GuestBookTest extends UnitTestConfig {

	private static final Logger logger = LoggerFactory.getLogger(GuestBookTest.class);
			
	@Autowired
	private GuestBookService guestBookService;
	
	@Test
//	@Ignore
	public void listTest() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pg", "1");
		map.put("spp", "10");
		map.put("key", "");
		map.put("word", "");
		List<GuestBookDto> list = guestBookService.listArticle(map);
		assertEquals(10, list.size());
//		logger.debug("list size : {}", list.size());
//		logger.debug("list : {}", list);
	}
	
	@Test
//	@Ignore
	public void modifyTest() throws Exception {
		GuestBookDto guestBookDto = new GuestBookDto();
		guestBookDto.setArticleno(1);
		guestBookDto.setSubject("제목 수정해요!!!!");
		guestBookDto.setContent("내용도 수정해요!!!");
		guestBookService.modifyArticle(guestBookDto);		
	}
	
}
