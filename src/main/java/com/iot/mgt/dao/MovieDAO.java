package com.iot.mgt.dao;

import java.util.List;
import com.iot.mgt.vo.RemoteMovieVO;

public interface MovieDAO {
	
	public List<RemoteMovieVO> selectMovie(long startnum, long endnum);

	public int selectAllCount();

	public List<RemoteMovieVO> selectBetweenDateMovie(long startnum, long endnum,
																			String startdate, String enddate);
	
	public int selectBetweenDateCount(String startdate, String enddate);
	
}