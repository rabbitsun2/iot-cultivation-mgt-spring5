package com.iot.mgt.dao;

import java.util.List;

import com.iot.mgt.vo.MemberVO;

public interface MemberDAO {

	public MemberVO selectMember(MemberVO member);
	public List<MemberVO> selectRngMember(long startnum, long endnum);
	public int selectAllCount();
	public int insertMember(MemberVO member);
	public int updateMember(MemberVO member);
	public int deleteMember(MemberVO member);
	
}