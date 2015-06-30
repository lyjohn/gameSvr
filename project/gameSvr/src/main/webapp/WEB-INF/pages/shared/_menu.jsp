<%@ page import="com.gamesvr.framework.util.Constants" %>
<%@ page import="com.gamesvr.po.SysUserExt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/core/include.jsp" %>
<%
    SysUserExt sessionUser = (SysUserExt) request.getSession().getAttribute(
            Constants.SESSION_USER);

    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    String menuStr = request.getParameter("menu");
    if (menuStr == null)
        menuStr = "0";
    int menu = Integer.valueOf(menuStr);
%>
<div class="sidebar-shortcuts" id="sidebar-shortcuts">
    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
        <button class="btn btn-success">
            <i class="icon-signal"></i>
        </button>

        <button class="btn btn-info">
            <i class="icon-pencil"></i>
        </button>

        <button class="btn btn-warning">
            <i class="icon-group"></i>
        </button>

        <button class="btn btn-danger">
            <i class="icon-cogs"></i>
        </button>
    </div>

    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
        <span class="btn btn-success"></span>

        <span class="btn btn-info"></span>

        <span class="btn btn-warning"></span>

        <span class="btn btn-danger"></span>
    </div>
</div>
<!-- #sidebar-shortcuts -->

<ul class="nav nav-list">
    <%if(menu>10 && menu < 20){%>
    <li class="active open">
    <%}
	else{%>
    <li>
    <%} %>
        <a href="#" class="dropdown-toggle">
            <i class="icon-th"></i>
            <span class="menu-text"> 服务器管理 </span>
            <b class="arrow icon-angle-down"></b>
        </a>

        <ul class="submenu">
            <% if(menu==11) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/server/index.do">
                    <i class="icon-double-angle-right"></i>
                    服务器列表
                </a>
            </li>

            <% if(menu==12) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/server/goCreate.do">
                    <i class="icon-double-angle-right"></i>
                    添加服务器
                </a>
            </li>
        </ul>
    </li>
    <%if(menu>20 && menu < 30){%>
    <li class="active open">
    <%} else{%>
    <li>
    <%} %>
        <a href="#" class="dropdown-toggle">
            <i class="icon-list"></i>
            <span class="menu-text"> 游戏管理 </span>
            <b class="arrow icon-angle-down"></b>
        </a>

        <ul class="submenu">
            <% if(menu==21) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/game/index.do">
                    <i class="icon-double-angle-right"></i>
                    游戏列表
                </a>
            </li>

            <% if(menu==22) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/game/goCreate.do">
                    <i class="icon-double-angle-right"></i>
                    添加游戏
                </a>
            </li>
        </ul>
    </li>
    <%if(menu>30 && menu < 40){%>
    <li class="active open">
    <%} else{%>
    <li>
    <%} %>
        <a href="#" class="dropdown-toggle">
            <i class="icon-dashboard"></i>
            <span class="menu-text"> 数据分析 </span>

            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <% if(menu==31) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/analysis/user.do">
                    <i class="icon-double-angle-right"></i>
                    用户分析
                </a>
            </li>

            <% if(menu==32) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
            <a href="${ctx}/analysis/flow.do">
                    <i class="icon-double-angle-right"></i>
                    流量统计
                </a>
            </li>
        </ul>
    </li>
    <% if (sessionUser.getIsAdmin()) {%>
    <%if(menu>40 && menu < 50){%>
    <li class="active open">
    <%} else{%>
    <li>
    <%} %>
        <a href="#" class="dropdown-toggle">
            <i class="icon-cogs"></i>
            <span class="menu-text"> 系统管理 </span>

            <b class="arrow icon-angle-down"></b>
        </a>

        <ul class="submenu">
            <% if(menu==41) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/admin/user/list.do">
                    <i class="icon-double-angle-right"></i>
                    用户管理
                </a>
            </li>
            <% if(menu==42) {%>
            <li class='active'>
            <%}else{ %>
            <li>
            <%} %>
                <a href="${ctx}/admin/oper/list.do">
                    <i class="icon-double-angle-right"></i>
                    操作记录
                </a>
            </li>
        </ul>
    </li>
    <%}%>
</ul>
<!-- /.nav-list -->

<div class="sidebar-collapse" id="sidebar-collapse">
    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
</div>