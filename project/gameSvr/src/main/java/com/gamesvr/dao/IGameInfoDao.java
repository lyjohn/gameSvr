package com.gamesvr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gamesvr.framework.util.Pagination;
import com.gamesvr.po.GameInfoExt;

@Component("gameInfoDao")
public interface IGameInfoDao {

	int create(GameInfoExt gameInfo);
	
	void update(GameInfoExt gameInfo);
	
	GameInfoExt load(@Param("id") Long  id);
	
	void delete(@Param("id") Long  id);

	List<GameInfoExt> criteriaQuery(@Param("mqlList") List<String> mqlList, @Param("mortList") List<String> mortList, @Param("p") Map<String, Object> p, @Param("pp") Pagination pp);
	
	int criteriaCount(@Param("mqlList") List<String> mqlList, @Param("p") Map<String, Object> p);

}
