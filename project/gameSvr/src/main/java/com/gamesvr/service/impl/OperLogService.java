package com.gamesvr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;
import com.gamesvr.framework.util.Constants;

import com.gamesvr.dao.IOperLogDao;
import com.gamesvr.po.OperLogExt;
import com.gamesvr.service.IOperLogService;

public class OperLogService implements IOperLogService{
	
	private IOperLogDao operLogDao;
	
	public IOperLogDao getOperLogDao() {
		return operLogDao;
	}

	public void setOperLogDao(IOperLogDao operLogDao) {
		this.operLogDao = operLogDao;
	}

	@Override
	public OperLogExt create(OperLogExt operLog) {
		int res = operLogDao.create(operLog);
		if(res == 1)
			return operLog;
		
		return null;
	}

	@Override
	public void update(OperLogExt operLog) {
		operLogDao.update(operLog);
	}
	
	@Override
	public OperLogExt load(Long id) {
		return operLogDao.load(id);
	}

	@Override
	public void delete(Long  id) {
		operLogDao.delete(id);
	}
	
	
	@Override
	public List<OperLogExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<OperLogExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<OperLogExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
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
		
		return operLogDao.criteriaQuery(mqlList, mortList, params, pp);
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
		
		return operLogDao.criteriaCount(mqlList,params);
	}
	
}
