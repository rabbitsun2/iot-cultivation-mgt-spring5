package com.iot.mgt.service;

import java.util.List;

import com.iot.mgt.vo.WaterPumpVO;

public interface WaterPumpService {

	public List<WaterPumpVO> selectWaterPump(long startnum, long endnum);
	public List<WaterPumpVO> selectBetweenWaterPump(long startnum, long endnum,
																			String startdate, String enddate);
																							
	public int selectAllCount();
	
	public int selectBetweenDateCount(String startdate, String enddate);
	
	public int insertWaterPump(WaterPumpVO waterPump);
	
}
