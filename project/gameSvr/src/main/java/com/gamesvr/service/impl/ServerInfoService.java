package com.gamesvr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;
import com.gamesvr.framework.util.Constants;

import com.gamesvr.dao.IServerInfoDao;
import com.gamesvr.po.ServerInfoExt;
import com.gamesvr.service.IServerInfoService;

public class ServerInfoService implements IServerInfoService{
	
	private IServerInfoDao serverInfoDao;
	
	public IServerInfoDao getServerInfoDao() {
		return serverInfoDao;
	}

	public void setServerInfoDao(IServerInfoDao serverInfoDao) {
		this.serverInfoDao = serverInfoDao;
	}

	@Override
	public ServerInfoExt create(ServerInfoExt serverInfo) {
		int res = serverInfoDao.create(serverInfo);
		if(res == 1)
			return serverInfo;
		
		return null;
	}

	@Override
	public void update(ServerInfoExt serverInfo) {
		serverInfoDao.update(serverInfo);
	}
	
	@Override
	public ServerInfoExt load(Long id) {
		return serverInfoDao.load(id);
	}

	@Override
	public void delete(Long  id) {
		serverInfoDao.delete(id);
	}
	
	
	@Override
	public List<ServerInfoExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<ServerInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<ServerInfoExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
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
		
		return serverInfoDao.criteriaQuery(mqlList, mortList, params, pp);
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
		
		return serverInfoDao.criteriaCount(mqlList,params);
	}
	
}
