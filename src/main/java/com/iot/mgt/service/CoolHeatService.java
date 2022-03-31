package com.iot.mgt.service;

import java.util.List;

import com.iot.mgt.vo.CoolHeatMonitorVO;

public interface CoolHeatService {

	public List<CoolHeatMonitorVO> selectCoolHeat(long startnum, long endnum);
	public List<CoolHeatMonitorVO> selectBetweenCoolHeat(long startnum, long endnum,
																			String startdate, String enddate);
																							
	public int selectAllCount();
	
	public int selectBetweenDateCount(String startdate, String enddate);
	
	public int insertCoolHeat(CoolHeatMonitorVO monitor);
	
}
