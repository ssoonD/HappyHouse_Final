package com.ssafy.guestbook.model.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.mapper.GuestBookMapper;
import com.ssafy.util.PageNavigation;

@Service
public class GuestBookServiceImpl implements GuestBookService {
	
	private static final Logger logger = LoggerFactory.getLogger(GuestBookServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional
	public void writeArticle(GuestBookDto guestBookDto) throws Exception {
		if(guestBookDto.getSubject() == null || guestBookDto.getContent() == null) {
			throw new Exception();
		}
		GuestBookMapper guestBookMapper = sqlSession.getMapper(GuestBookMapper.class);
		guestBookMapper.writeArticle(guestBookDto);
		logger.debug("글번호 : {}", guestBookDto.getArticleno());
		if(guestBookDto.getFileInfos() != null) {
			logger.debug("업로드 파일 수 : {}", guestBookDto.getFileInfos().size());
			guestBookMapper.fileRegist(guestBookDto);
		}
	}

	@Override
	public List<GuestBookDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
//		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(GuestBookMapper.class).listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
//		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(GuestBookMapper.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public GuestBookDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(GuestBookMapper.class).getArticle(articleno);
	}

	@Override
	public void modifyArticle(GuestBookDto guestBookDto) throws Exception {
		sqlSession.getMapper(GuestBookMapper.class).modifyArticle(guestBookDto);
	}

	@Override
	public void deleteArticle(int articleno) throws Exception {
		sqlSession.getMapper(GuestBookMapper.class).deleteArticle(articleno);
	}

}
