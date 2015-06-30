package com.gamesvr.service;

import java.util.List;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.po.OperLogExt;

public interface IOperLogService {
	
	public OperLogExt create(OperLogExt operLog);
	
	public void update(OperLogExt operLog);
	
	public OperLogExt load(Long  id);
	
	public void delete(Long  id);

	public List<OperLogExt> criteriaQuery(List<ICondition> conditions);
	
	public List<OperLogExt> criteriaQuery(List<ICondition> conditions, List<Order> orders);
	
	public List<OperLogExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp);
	
	public int count(List<ICondition> conditions);
	
}
