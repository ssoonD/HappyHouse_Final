package com.ssafy.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.NoticeDao;
import com.ssafy.edu.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;

	@Override
	@Transactional(readOnly = true)
	public List<NoticeDto> findAllNotice() throws Exception {
		return noticeDao.findAllNotice();
	}

	@Override
	@Transactional(readOnly = true)
	public List<NoticeDto> findNoticeByTitle(String title) throws Exception {
		return noticeDao.findNoticeByTitle(title);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NoticeDto> findNoticeByUsername(String username) throws Exception {
		return noticeDao.findNoticeByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public NoticeDto findNoticeByIdx(int idx) throws Exception {
		return noticeDao.findNoticeByIdx(idx);
	}

	@Override
	@Transactional(readOnly = true)
	public int getNoticesTotal() throws Exception {
		return noticeDao.getNoticesTotal();
	}

	@Override
	@Transactional
	public boolean addNotice(NoticeDto notice) throws Exception {
		return noticeDao.addNotice(notice);
	}

	@Override
	@Transactional
	public boolean updateNotice(NoticeDto notice) throws Exception {
		return noticeDao.updateNotice(notice);
	}

	@Override
	@Transactional
	public boolean deleteNotice(int idx) throws Exception {
		return noticeDao.deleteNotice(idx);
	}

}
