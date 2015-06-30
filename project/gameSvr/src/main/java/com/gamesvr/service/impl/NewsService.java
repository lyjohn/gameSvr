package com.gamesvr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;
import com.gamesvr.framework.util.Constants;

import com.gamesvr.dao.INewsDao;
import com.gamesvr.po.NewsExt;
import com.gamesvr.service.INewsService;

public class NewsService implements INewsService{
	
	private INewsDao newsDao;
	
	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public NewsExt create(NewsExt news) {
		int res = newsDao.create(news);
		if(res == 1)
			return news;
		
		return null;
	}

	@Override
	public void update(NewsExt news) {
		newsDao.update(news);
	}
	
	@Override
	public NewsExt load(Long id) {
		return newsDao.load(id);
	}

	@Override
	public void delete(Long  id) {
		newsDao.delete(id);
	}
	
	
	@Override
	public List<NewsExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<NewsExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<NewsExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
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
		
		return newsDao.criteriaQuery(mqlList, mortList, params, pp);
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
		
		return newsDao.criteriaCount(mqlList,params);
	}
	
}
