package com.gamesvr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;
import com.gamesvr.framework.util.Constants;

import com.gamesvr.dao.IGameServerDao;
import com.gamesvr.po.GameServerExt;
import com.gamesvr.service.IGameServerService;

public class GameServerService implements IGameServerService{
	
	private IGameServerDao gameServerDao;
	
	public IGameServerDao getGameServerDao() {
		return gameServerDao;
	}

	public void setGameServerDao(IGameServerDao gameServerDao) {
		this.gameServerDao = gameServerDao;
	}

	@Override
	public GameServerExt create(GameServerExt gameServer) {
		int res = gameServerDao.create(gameServer);
		if(res == 1)
			return gameServer;
		
		return null;
	}

	@Override
	public void update(GameServerExt gameServer) {
		gameServerDao.update(gameServer);
	}
	
	@Override
	public GameServerExt load(Long id) {
		return gameServerDao.load(id);
	}

	@Override
	public void delete(Long  id) {
		gameServerDao.delete(id);
	}
	
	
	@Override
	public List<GameServerExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<GameServerExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<GameServerExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
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
		
		return gameServerDao.criteriaQuery(mqlList, mortList, params, pp);
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
		
		return gameServerDao.criteriaCount(mqlList,params);
	}
	
}
