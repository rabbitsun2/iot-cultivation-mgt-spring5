package com.iot.mgt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.iot.mgt.dao.MemberDAO;
import com.iot.mgt.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public MemberVO selectMember(MemberVO member) {
		
		return dao.selectMember(member);
	}
	
	@Override
	public List<MemberVO> selectRngMember(long startnum, long endnum) {
		
		return dao.selectRngMember(startnum, endnum);
	}

	@Override
	public int selectAllCount() {
		
		return dao.selectAllCount();
	}
	
	@Override
	public int insertMember(MemberVO member) {
		
		return dao.insertMember(member);
		
	}

	@Override
	public int updateMember(MemberVO member) {
		
		return dao.updateMember(member);
		
	}

	@Override
	public int deleteMember(MemberVO member) {
		
		return dao.deleteMember(member);
		
	}
	
}