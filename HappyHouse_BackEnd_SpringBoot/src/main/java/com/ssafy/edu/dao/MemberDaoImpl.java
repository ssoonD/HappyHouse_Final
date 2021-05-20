package com.ssafy.edu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	private String ns="com.ssafy.edu.dao.MemberMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MemberDto> findAllMember() throws Exception {
		return sqlSession.selectList(ns + "findAllMember");
	}

	@Override
	public MemberDto findMemberById(String userid) throws Exception {
		return sqlSession.selectOne(ns + "findMemberById", userid);
	}

	@Override
	public int getMembersTotal() throws Exception {
		return sqlSession.selectOne(ns + "getMembersTotal");
	}

	@Override
	public boolean addMember(MemberDto member) throws Exception {
		sqlSession.insert(ns + "addMember", member);
		return true;
	}

	@Override
	public boolean updateMember(MemberDto member) throws Exception {
		sqlSession.update(ns + "updateMember", member);
		return true;
	}

	@Override
	public boolean deleteMember(String userid) throws Exception {
		sqlSession.delete(ns + "deleteMember", userid);
		return true;
	}

}
