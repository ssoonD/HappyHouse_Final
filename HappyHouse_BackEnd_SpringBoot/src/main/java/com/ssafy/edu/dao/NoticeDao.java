package com.ssafy.edu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.edu.dto.NoticeDto;

public interface NoticeDao {
	// 공지사항 리스트
	public List<NoticeDto> findAllNotice() throws Exception;

	// 제목으로 검색
	public List<NoticeDto> findNoticeByTitle(String title) throws Exception;

	// 회원 이름으로 검색
	public List<NoticeDto> findNoticeByUsername(String username) throws Exception;

	// idx으로 검색
	public NoticeDto findNoticeByIdx(int idx) throws Exception;

	// 전체 공지사항 수
	public int getNoticesTotal() throws Exception;

	// 공지사항 등록
	public boolean addNotice(NoticeDto notice) throws Exception;

	// 공지사항 수정
	public boolean updateNotice(NoticeDto notice) throws Exception;

	// 공지사항 삭제
	public boolean deleteNotice(int idx) throws Exception;
}
