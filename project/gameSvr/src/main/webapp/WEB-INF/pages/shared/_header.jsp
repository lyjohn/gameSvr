<%@ page import="com.gamesvr.framework.util.Constants" %>
<%@ page import="com.gamesvr.po.SysUserExt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/core/include.jsp" %>
<%
    SysUserExt sessionUser = (SysUserExt) request.getSession().getAttribute(
            Constants.SESSION_USER);
%>
<div class="navbar-container" id="navbar-container">
    <div class="navbar-header pull-left">
        <a href="#" class="navbar-brand">
            <small>
                <i class="icon-leaf"></i>
                游戏服务器管理系统
            </small>
        </a><!-- /.brand -->
    </div><!-- /.navbar-header -->

    <div class="navbar-header pull-right" role="navigation">
        <ul class="nav ace-nav">
            <li class="light-blue">
                <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                    <img class="nav-user-photo" src="${ctx}/avatar/user/<%=sessionUser.getId()%>.do" alt="<%=sessionUser.getUserName()%>" />
								<span class="user-info">
									<small>欢迎光临,</small>
									<%=sessionUser.getUserName()%>
								</span>

                    <i class="icon-caret-down"></i>
                </a>

                <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                    <li>
                        <a href="${ctx}/index">
                            <i class="icon-user"></i>
                            个人资料
                        </a>
                    </li>

                    <li class="divider"></li>

                    <li>
                        <a href="${ctx}/doSignOut.do">
                            <i class="icon-off"></i>
                            退出
                        </a>
                    </li>
                </ul>
            </li>
        </ul><!-- /.ace-nav -->
    </div><!-- /.navbar-header -->
</div><!-- /.container -->