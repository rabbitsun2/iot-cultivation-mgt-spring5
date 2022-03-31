package com.iot.mgt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.iot.mgt.dao.WaterPumpDAO;
import com.iot.mgt.vo.WaterPumpVO;

@Service
public class WaterPumpServiceImpl implements WaterPumpService {

	@Inject
	private WaterPumpDAO dao;
	
	@Override
	public List<WaterPumpVO> selectWaterPump(long startnum, long endnum) {

		return dao.selectWaterPump(startnum, endnum);
	}

	@Override
	public List<WaterPumpVO> selectBetweenWaterPump(long startnum, long endnum, String startdate, String enddate) {

		return dao.selectBetweenDateWaterPump(startnum, endnum, startdate, enddate);
	}

	@Override
	public int selectAllCount() {
		
		return dao.selectAllCount();
	}

	@Override
	public int selectBetweenDateCount(String startdate, String enddate) {
		
		return dao.selectBetweenDateCount(startdate, enddate);
	}

	@Override
	public int insertWaterPump(WaterPumpVO waterPump) {
		
		return dao.insertWaterPump(waterPump);
	}

}