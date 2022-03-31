package com.iot.mgt.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.iot.mgt.dao.CoolHeatDAO;
import com.iot.mgt.vo.CoolHeatMonitorVO;

@Service
public class CoolHeatServiceImpl implements CoolHeatService{

	@Inject
	private CoolHeatDAO dao;
	
	@Override
	public List<CoolHeatMonitorVO> selectCoolHeat(long startnum, long endnum) {

		return dao.selectCoolHeat(startnum, endnum);
	}

	@Override
	public List<CoolHeatMonitorVO> selectBetweenCoolHeat(long startnum, long endnum, String startdate, String enddate) {

		return dao.selectBetweenDateCoolHeat(startnum, endnum, startdate, enddate);
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
	public int insertCoolHeat(CoolHeatMonitorVO monitor) {

		return dao.insertCoolHeat(monitor);
	}

}
