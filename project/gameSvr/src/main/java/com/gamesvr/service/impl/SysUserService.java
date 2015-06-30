package com.gamesvr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.dao.ISysUserDao;
import com.gamesvr.po.SysUserExt;
import com.gamesvr.service.ISysUserService;

public class SysUserService implements ISysUserService{
	
	private ISysUserDao sysUserDao;
	
	public ISysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(ISysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	@Override
	public SysUserExt create(SysUserExt sysUser) {
		int res = sysUserDao.create(sysUser);
		if(res == 1)
			return sysUser;
		
		return null;
	}

	@Override
	public void update(SysUserExt sysUser) {
		sysUserDao.update(sysUser);
	}
	
	@Override
	public SysUserExt load(String id) {
		return sysUserDao.load(id);
	}

	@Override
	public void delete(String  id) {
		sysUserDao.delete(id);
	}

	@Override
	public List<SysUserExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<SysUserExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<SysUserExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
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
		
		return sysUserDao.criteriaQuery(mqlList, mortList, params, pp);
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
		
		return sysUserDao.criteriaCount(mqlList,params);
	}
	
}
