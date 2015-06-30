package com.gamesvr.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamesvr.framework.servlet.WebContext;
import com.gamesvr.framework.util.Constants;
import com.gamesvr.po.SysUserExt;

/*
 * 此Filter用于在用户访问时，判断用户时候是非法访问
 * 主要判断用户的Session是否存在，如果存在，则正常访问，如果不存在，则跳转到登录页面
 */

public class AdminFilter implements Filter {

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		SysUserExt user = (SysUserExt) session.getAttribute(Constants.SESSION_USER);

		if(user == null || !user.getIsAdmin()){
			response.sendRedirect(WebContext.contextPath + "/login/goSignout.do");
		}else{
			chain.doFilter(request, response);
		}
	}

	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}