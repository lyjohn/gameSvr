package com.gamesvr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gamesvr.framework.util.Pagination;
import com.gamesvr.po.ServerInfoExt;

@Component("serverInfoDao")
public interface IServerInfoDao {

	int create(ServerInfoExt serverInfo);
	
	void update(ServerInfoExt serverInfo);
	
	ServerInfoExt load(@Param("id") Long  id);
	
	void delete(@Param("id") Long  id);

	List<ServerInfoExt> criteriaQuery(@Param("mqlList") List<String> mqlList, @Param("mortList") List<String> mortList, @Param("p") Map<String, Object> p, @Param("pp") Pagination pp);
	
	int criteriaCount(@Param("mqlList") List<String> mqlList, @Param("p") Map<String, Object> p);

}
