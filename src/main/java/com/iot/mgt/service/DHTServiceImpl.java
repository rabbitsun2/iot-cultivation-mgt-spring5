package com.iot.mgt.service;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.iot.mgt.dao.DHTDAO;
import com.iot.mgt.vo.SensorDHTVO;
import com.iot.mgt.vo.SpecificDHTVO;

@Service
public class DHTServiceImpl implements DHTService {

	@Inject
	private DHTDAO dao;
	
	@Override
	public List<SensorDHTVO> selectDHT(long startnum, long endnum) {
		
		return dao.selectDHT(startnum, endnum);
	}

	@Override
	public List<SensorDHTVO> selectBetweenDHT(long startnum, long endnum, String startdate, String enddate) {
		
		return dao.selectBetweenDateDHT(startnum, endnum, startdate, enddate);
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
	public List<SpecificDHTVO> selectSpecificDHT(Date startdate) {
		
		return dao.selectSpecificDHT(startdate);
	}

	@Override
	public int insertDHT(SensorDHTVO sensorDHTVO) {
		
		return dao.insertDHT(sensorDHTVO);
	}
	
}