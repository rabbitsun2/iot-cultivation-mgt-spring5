package com.iot.mgt.dao;

import java.util.Date;
import java.util.List;
import com.iot.mgt.vo.SensorDHTVO;
import com.iot.mgt.vo.SpecificDHTVO;

public interface DHTDAO {

	public List<SensorDHTVO> selectDHT(long startnum, long endnum);
	public List<SensorDHTVO> selectBetweenDateDHT(long startnum, long endnum,
																			String startdate, String enddate);
																							
	public int selectAllCount();
	
	public int selectBetweenDateCount(String startdate, String enddate);

	public List<SpecificDHTVO> selectSpecificDHT(Date startdate);
	
	public int insertDHT(SensorDHTVO sensorDHTVO);
	
}