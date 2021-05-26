package com.ssafy.edu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.edu.dto.MemberDto;

public interface MemberDao {
	// 멤버 리스트
	public List<MemberDto> findAllMember() throws Exception;

	// 아이디로 찾기
	public MemberDto findMemberById(String userid) throws Exception;

	// 이름으로 찾기
	public MemberDto findMemberByName(String username) throws Exception;

	// 전체 멤버 수
	public int getMembersTotal() throws Exception;

	// 회원 등록
	public boolean addMember(MemberDto member) throws Exception;

	// 회원 정보 수정
	public boolean updateMember(MemberDto member) throws Exception;

	// 회원 삭제
	public boolean deleteMember(String userid) throws Exception;
}
