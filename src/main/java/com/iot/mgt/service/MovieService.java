package com.iot.mgt.service;

import java.util.List;
import com.iot.mgt.vo.RemoteMovieVO;

public interface MovieService {
	
	public List<RemoteMovieVO> selectMovie(long startnum, long endnum);
	public List<RemoteMovieVO> selectBetweenDateMovie(long startnum, long endnum,
																			String startdate, String enddate);
																							
	public int selectAllCount();
	
	public int selectBetweenDateCount(String startdate, String enddate);
	
}