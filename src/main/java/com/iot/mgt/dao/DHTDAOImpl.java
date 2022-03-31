package com.iot.mgt.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.iot.mgt.vo.SensorDHTVO;
import com.iot.mgt.vo.SpecificDHTVO;

@Repository
public class DHTDAOImpl implements DHTDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.iot.mgt.mapper.DHTMapper";
	
	@Override
	public List<SensorDHTVO> selectDHT(long startnum, long endnum) {


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
		
		return sqlSession.selectList(ns + ".selectDHT", paramMap);
		
	}

	@Override
	public List<SensorDHTVO> selectBetweenDateDHT(long startnum, long endnum, String startdate, String enddate) {

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
		
		return sqlSession.selectList(ns + ".selectBetweenDateDHT", paramMap);
		
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
	public List<SpecificDHTVO> selectSpecificDHT(Date startdate) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd 00:00:00");	// 날짜 양식
		
		paramMap.put("startdate", format1.format(startdate));
		
		return sqlSession.selectList(ns + ".selectSpecificDHT", paramMap);
	}

	@Override
	public int insertDHT(SensorDHTVO sensorDHTVO) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	// 날짜 양식
		
		paramMap.put("uuid", sensorDHTVO.getUuid());
		paramMap.put("regidate", format1.format(sensorDHTVO.getRegidate()));
		paramMap.put("location", sensorDHTVO.getLocation());
		paramMap.put("temperature", sensorDHTVO.getTemperature());
		paramMap.put("humidity", sensorDHTVO.getHumidity());
		paramMap.put("ipv4", sensorDHTVO.getIpv4());
		paramMap.put("ipv6", sensorDHTVO.getIpv6());
		
		return sqlSession.insert(ns + ".insertDHT", paramMap);
		
	}

}