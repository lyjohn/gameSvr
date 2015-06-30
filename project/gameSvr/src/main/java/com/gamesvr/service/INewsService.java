package com.gamesvr.service;

import java.util.List;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.po.NewsExt;

public interface INewsService {
	
	public NewsExt create(NewsExt news);
	
	public void update(NewsExt news);
	
	public NewsExt load(Long  id);
	
	public void delete(Long  id);

	public List<NewsExt> criteriaQuery(List<ICondition> conditions);
	
	public List<NewsExt> criteriaQuery(List<ICondition> conditions, List<Order> orders);
	
	public List<NewsExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp);
	
	public int count(List<ICondition> conditions);
	
}
