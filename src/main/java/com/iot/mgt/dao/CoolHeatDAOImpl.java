package com.iot.mgt.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iot.mgt.vo.CoolHeatMonitorVO;

@Repository
public class CoolHeatDAOImpl implements CoolHeatDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.iot.mgt.mapper.CoolHeatMapper";
	
	@Override
	public List<CoolHeatMonitorVO> selectCoolHeat(long startnum, long endnum) {

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
		
		return sqlSession.selectList(ns + ".selectCoolHeat", paramMap);
		
	}

	@Override
	public List<CoolHeatMonitorVO> selectBetweenDateCoolHeat(long startnum, long endnum, 
																									String startdate, String enddate) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
						
		// mariaDB 페이징
		if ( startnum == 1) {
			paramMap.put("startnum", 0);
		}
		else {
			paramMap.put("startnum", startnum - 1);
		}
		
		paramMap.put("limnum", ( endnum - startnum) + 1);
		
		paramMap.put("startdate", startdate);
		paramMap.put("enddate", enddate);
		
		return sqlSession.selectList(ns + ".selectBetweenDateCoolHeat", paramMap);
		
	}

	@Override
	public int selectAllCount() {
		
		return sqlSession.selectOne(ns + ".selectAllCount");
		
	}

	@Override
	public int selectBetweenDateCount(String startdate, String enddate) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("startdate", startdate);
		paramMap.put("enddate", enddate);
		
		return sqlSession.selectOne(ns + ".selectBetweenDateCount", paramMap);
		
	}

	@Override
	public int insertCoolHeat(CoolHeatMonitorVO monitor) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	// 날짜 양식
		
		//System.out.println("현재:" + format1.format(monitor.getCreateDate()));
		//System.out.println("냉방 상태:" + monitor.getCool_state());
		
		paramMap.put("createDate", format1.format(monitor.getCreateDate()));
		paramMap.put("location", monitor.getLocation());
		paramMap.put("cool_state", monitor.getCool_state());
		paramMap.put("heat_state", monitor.getHeat_state());
		paramMap.put("ipv4", monitor.getIpv4());
		paramMap.put("ipv6", monitor.getIpv6());
		
		return sqlSession.insert(ns + ".insertCoolHeatMonitor", paramMap);
		
	}
	
}