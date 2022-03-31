package com.iot.mgt.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.iot.mgt.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.iot.mgt.mapper.MemberMapper";
	
	@Override
	public MemberVO selectMember(MemberVO member) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("email", member.getEmail());
		
		return sqlSession.selectOne(ns + ".selectMember", paramMap);
		
	}

	@Override
	public List<MemberVO> selectRngMember(long startnum, long endnum) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
				
		//	오라클 페이징
		//paramMap.put("startnum", startnum);		
		//paramMap.put("endnum", endnum);			
		
		// mariaDB 페이징
		if ( startnum == 1) {
			paramMap.put("startnum", 0);
		}
		else {
			paramMap.put("startnum", startnum - 1);
		}
		
		paramMap.put("limnum", ( endnum - startnum) + 1);			
		
		return sqlSession.selectList(ns + ".selectRngMember", paramMap);
		
	}

	@Override
	public int selectAllCount() {
		
		return sqlSession.selectOne(ns + ".selectAllCount");
	}
	
	@Override
	public int insertMember(MemberVO member) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd" );	// 날짜 양식
		
		paramMap.put("email", member.getEmail());
		paramMap.put("uuid", member.getUuid());
		paramMap.put("passwd", member.getPasswd());
		paramMap.put("usrname", member.getUsrname());
		paramMap.put("birthdate", format1.format(member.getBirthdate()));
		paramMap.put("remember", member.getRemember());
		paramMap.put("usrgrant", member.getUsrgrant());
		paramMap.put("locked", member.getLocked());
		
		format1.applyPattern("yyyy-MM-dd hh:mm:ss");
		paramMap.put("regidate", format1.format( member.getRegidate()));
		
		return sqlSession.insert(ns + ".insertMember", paramMap);
		
	}

	@Override
	public int updateMember(MemberVO member) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd" );	// 날짜 양식
		
		paramMap.put("passwd", member.getPasswd());
		paramMap.put("birthdate", format1.format(member.getBirthdate()));
		paramMap.put("usrgrant", member.getUsrgrant());
		paramMap.put("locked", member.getLocked());
		paramMap.put("email", member.getEmail());
				
		return sqlSession.update(ns + ".updateMember", paramMap);
		
	}

	@Override
	public int deleteMember(MemberVO member) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", member.getId());
		
		return sqlSession.delete(ns + ".deleteMember", paramMap);
		
	}
	
}