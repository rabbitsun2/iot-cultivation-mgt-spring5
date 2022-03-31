package com.iot.mgt.dao;

import java.util.List;
import com.iot.mgt.vo.CoolHeatMonitorVO;

public interface CoolHeatDAO {

	public List<CoolHeatMonitorVO> selectCoolHeat(long startnum, long endnum);
	public List<CoolHeatMonitorVO> selectBetweenDateCoolHeat(long startnum, long endnum,
																			String startdate, String enddate);
																							
	public int selectAllCount();
	
	public int selectBetweenDateCount(String startdate, String enddate);

	public int insertCoolHeat(CoolHeatMonitorVO monitor);
	
}