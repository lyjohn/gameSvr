package com.gamesvr.service;

import java.util.List;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.po.ServerInfoExt;

public interface IServerInfoService {
	
	public ServerInfoExt create(ServerInfoExt serverInfo);
	
	public void update(ServerInfoExt serverInfo);
	
	public ServerInfoExt load(Long  id);
	
	public void delete(Long  id);

	public List<ServerInfoExt> criteriaQuery(List<ICondition> conditions);
	
	public List<ServerInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders);
	
	public List<ServerInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp);
	
	public int count(List<ICondition> conditions);
	
}
