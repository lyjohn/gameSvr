package com.gamesvr.service;

import java.util.List;

import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Pagination;

import com.gamesvr.po.SysUserExt;

public interface ISysUserService {
	
	public SysUserExt create(SysUserExt sysUser);

	public void update(SysUserExt sysUser);
	
	public SysUserExt load(String  id);
	
	public void delete(String  id);

	public List<SysUserExt> criteriaQuery(List<ICondition> conditions);
	
	public List<SysUserExt> criteriaQuery(List<ICondition> conditions, List<Order> orders);

	public List<SysUserExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp);
	
	public int count(List<ICondition> conditions);
	
}
