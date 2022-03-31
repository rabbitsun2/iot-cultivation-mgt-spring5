package com.iot.mgt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.iot.mgt.dao.MovieDAO;
import com.iot.mgt.vo.RemoteMovieVO;

@Service
public class MovieServiceImpl implements MovieService {

	@Inject
	private MovieDAO dao;
	
	@Override
	public List<RemoteMovieVO> selectMovie(long startnum, long endnum) {
		
		return dao.selectMovie(startnum, endnum);
		
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
	public List<RemoteMovieVO> selectBetweenDateMovie(long startnum, long endnum, 
																				String startdate, String enddate) {
		
		return dao.selectBetweenDateMovie(startnum, endnum, startdate, enddate);
		
	}
	
}