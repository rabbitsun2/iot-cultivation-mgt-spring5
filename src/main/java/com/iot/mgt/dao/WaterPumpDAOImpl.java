package com.iot.mgt.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iot.mgt.vo.WaterPumpVO;

@Repository
public class WaterPumpDAOImpl implements WaterPumpDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.iot.mgt.mapper.WaterPumpMapper";
	
	@Override
	public List<WaterPumpVO> selectWaterPump(long startnum, long endnum) {

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
		
		return sqlSession.selectList(ns + ".selectWaterPump", paramMap);
		
	}

	@Override
	public List<WaterPumpVO> selectBetweenDateWaterPump(long startnum, long endnum, String startdate, String enddate) {
		
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
		
		return sqlSession.selectList(ns + ".selectBetweenDateWaterPump", paramMap);
		
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
	public int insertWaterPump(WaterPumpVO waterPump) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	// 날짜 양식
				
		//System.out.println("현재:" + format1.format(monitor.getCreateDate()));
		//System.out.println("냉방 상태:" + monitor.getCool_state());
		
		paramMap.put("createDate", format1.format(waterPump.getCreateDate()));
		paramMap.put("location", waterPump.getLocation());
		paramMap.put("water_state", waterPump.getWater_state());
		paramMap.put("memo", waterPump.getMemo());
		paramMap.put("ipv4", waterPump.getIpv4());
		paramMap.put("ipv6", waterPump.getIpv6());
		
		return sqlSession.insert(ns + ".insertWaterPump", paramMap);
		
	}
	
}