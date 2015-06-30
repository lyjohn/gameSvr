package com.gamesvr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;
import com.gamesvr.framework.util.Constants;

import com.gamesvr.dao.IGameInfoDao;
import com.gamesvr.po.GameInfoExt;
import com.gamesvr.service.IGameInfoService;

public class GameInfoService implements IGameInfoService{
	
	private IGameInfoDao gameInfoDao;
	
	public IGameInfoDao getGameInfoDao() {
		return gameInfoDao;
	}

	public void setGameInfoDao(IGameInfoDao gameInfoDao) {
		this.gameInfoDao = gameInfoDao;
	}

	@Override
	public GameInfoExt create(GameInfoExt gameInfo) {
		int res = gameInfoDao.create(gameInfo);
		if(res == 1)
			return gameInfo;
		
		return null;
	}

	@Override
	public void update(GameInfoExt gameInfo) {
		gameInfoDao.update(gameInfo);
	}
	
	@Override
	public GameInfoExt load(Long id) {
		return gameInfoDao.load(id);
	}

	@Override
	public void delete(Long  id) {
		gameInfoDao.delete(id);
	}
	
	
	@Override
	public List<GameInfoExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<GameInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<GameInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> mqlList = new ArrayList<String>();
		if(conditions != null){
			for(ICondition condition : conditions){
				mqlList.add(condition.generateExpression(params));
			}
		}
		
		List<String> mortList = new ArrayList<String>();
		if(orders != null){
			for(Order order : orders){
				mortList.add(order.toSqlString());
			}
		}
		
		return gameInfoDao.criteriaQuery(mqlList, mortList, params, pp);
	}

	@Override
	public int count(List<ICondition> conditions) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> mqlList = new ArrayList<String>();
		if(conditions != null){
			for(ICondition condition : conditions){
				mqlList.add(condition.generateExpression(params));
			}
		}
		
		return gameInfoDao.criteriaCount(mqlList,params);
	}
	
}
