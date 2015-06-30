package com.gamesvr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gamesvr.framework.util.Pagination;
import com.gamesvr.po.SysUserExt;

@Component("sysUserDao")
public interface ISysUserDao {

	int create(SysUserExt sysUser);
	
	void update(SysUserExt sysUser);
	
	SysUserExt load(@Param("id") String  id);
	
	void delete(@Param("id") String  id);

	List<SysUserExt> criteriaQuery(@Param("mqlList") List<String> mqlList, @Param("mortList") List<String> mortList, @Param("p") Map<String, Object> p, @Param("pp") Pagination pp);
	
	int criteriaCount(@Param("mqlList") List<String> mqlList, @Param("p") Map<String, Object> p);

}
