package com.ssafy.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.MemberDao;
import com.ssafy.edu.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	@Transactional(readOnly = true)
	public List<MemberDto> findAllMember() throws Exception {
		return memberDao.findAllMember();
	}

	@Override
	@Transactional(readOnly = true)
	public MemberDto findMemberById(String userid) throws Exception {
		return memberDao.findMemberById(userid);
	}

	@Override
	@Transactional(readOnly = true)
	public int getMembersTotal() throws Exception {
		return memberDao.getMembersTotal();
	}

	@Override
	@Transactional
	public boolean addMember(MemberDto member) throws Exception {
		return memberDao.addMember(member);
	}

	@Override
	@Transactional
	public boolean updateMember(MemberDto member) throws Exception {
		return memberDao.updateMember(member);
	}

	@Override
	@Transactional
	public boolean deleteMember(String userid) throws Exception {
		return memberDao.deleteMember(userid);
	}

}
