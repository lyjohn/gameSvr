package com.gamesvr.service;

import java.util.List;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.po.GameServerExt;

public interface IGameServerService {
	
	public GameServerExt create(GameServerExt gameServer);
	
	public void update(GameServerExt gameServer);
	
	public GameServerExt load(Long  id);
	
	public void delete(Long  id);

	public List<GameServerExt> criteriaQuery(List<ICondition> conditions);
	
	public List<GameServerExt> criteriaQuery(List<ICondition> conditions, List<Order> orders);
	
	public List<GameServerExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp);
	
	public int count(List<ICondition> conditions);
	
}
