package com.iot.mgt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.iot.mgt.vo.RemoteMovieVO;

@Repository
public class MovieDAOImpl implements MovieDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.iot.mgt.mapper.MovieMapper";
	
	@Override
	public List<RemoteMovieVO> selectMovie(long startnum, long endnum) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
				
		//	오라클 페이징
		//paramMap.put("startnum", startnum);		
		//paramMap.put("endnum", endnum);			
		
		// mariaDB 페이징
		if ( startnum == 1) {
			paramMap.put("startnum", 0);
		}
		else {
			paramMap.put("startnum", startnum - 1);
		}
		
		paramMap.put("limnum", ( endnum - startnum) + 1);			
		
		return sqlSession.selectList(ns + ".selectRemoteMovie", paramMap);
		
	}

	@Override
	public int selectAllCount() {

		return sqlSession.selectOne(ns + ".selectAllCount");
		
	}

	@Override
	public int selectBetweenDateCount(String startdate, String enddate) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("startdate", startdate);
		paramMap.put("enddate", enddate);
		
		return sqlSession.selectOne(ns + ".selectBetweenDateCount", paramMap);
		
	}

	@Override
	public List<RemoteMovieVO> selectBetweenDateMovie(long startnum, long endnum, String startdate, String enddate) {
		

		Map<String, Object> paramMap = new HashMap<String, Object>();
						
		// mariaDB 페이징
		if ( startnum == 1) {
			paramMap.put("startnum", 0);
		}
		else {
			paramMap.put("startnum", startnum - 1);
		}
		
		paramMap.put("limnum", ( endnum - startnum) + 1);
		
		paramMap.put("startdate", startdate);
		paramMap.put("enddate", enddate);
		
		return sqlSession.selectList(ns + ".selectBetweenDateRemoteMovie", paramMap);
		
	}

}