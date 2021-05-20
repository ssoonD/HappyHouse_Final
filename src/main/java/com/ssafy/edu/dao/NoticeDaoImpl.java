package com.ssafy.edu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.dto.NoticeDto;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	private String ns = "com.ssafy.edu.dao.NoticeMapper.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeDto> findAllNotice() throws Exception {
		return sqlSession.selectList(ns + "findAllNotice");
	}

	@Override
	public List<NoticeDto> findNoticeByTitle(String title) throws Exception {
		return sqlSession.selectList(ns + "findNoticeByTitle", title);
	}
	
	@Override
	public List<NoticeDto> findNoticeByUsername(String username) throws Exception {
		return sqlSession.selectList(ns + "findNoticeByUsername", username);
	}
	
	@Override
	public NoticeDto findNoticeByIdx(int idx) throws Exception {
		return sqlSession.selectOne(ns + "findNoticeByIdx", idx);
	}

	@Override
	public int getNoticesTotal() throws Exception {
		return sqlSession.selectOne(ns + "getNoticesTotal");
	}

	@Override
	public boolean addNotice(NoticeDto notice) throws Exception {
		sqlSession.insert(ns + "addNotice", notice);
		return true;
	}

	@Override
	public boolean updateNotice(NoticeDto notice) throws Exception {
		sqlSession.update(ns + "updateNotice", notice);
		return true;
	}

	@Override
	public boolean deleteNotice(int idx) throws Exception {
		sqlSession.delete(ns + "deleteNotice", idx);
		return true;
	}

}
