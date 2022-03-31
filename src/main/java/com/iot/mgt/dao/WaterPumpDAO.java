package com.iot.mgt.dao;

import java.util.List;
import com.iot.mgt.vo.WaterPumpVO;

public interface WaterPumpDAO {

	public List<WaterPumpVO> selectWaterPump(long startnum, long endnum);
	public List<WaterPumpVO> selectBetweenDateWaterPump(long startnum, long endnum,
																			String startdate, String enddate);
																							
	public int selectAllCount();
	
	public int selectBetweenDateCount(String startdate, String enddate);

	public int insertWaterPump(WaterPumpVO waterPump);
	
}