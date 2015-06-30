package com.gamesvr.service;

import java.util.List;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.po.GameInfoExt;

public interface IGameInfoService {
	
	public GameInfoExt create(GameInfoExt gameInfo);
	
	public void update(GameInfoExt gameInfo);
	
	public GameInfoExt load(Long  id);
	
	public void delete(Long  id);

	public List<GameInfoExt> criteriaQuery(List<ICondition> conditions);
	
	public List<GameInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders);
	
	public List<GameInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp);
	
	public int count(List<ICondition> conditions);
	
}
