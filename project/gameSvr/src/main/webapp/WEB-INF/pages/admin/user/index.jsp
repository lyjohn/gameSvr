<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/core/include.jsp" %>

<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/font-awesome.min.css">

  <!--[if IE 7]>
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/font-awesome-ie7.min.css"/>
  <![endif]-->

  <!-- page specific plugin styles -->
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/jquery-ui-1.10.3.custom.min.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/jquery.gritter.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/select2.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/bootstrap-editable.css">

  <!-- page specific plugin styles -->
  <!-- ace styles -->
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ace.min.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ace-rtl.min.css">

  <!--[if lte IE 8]>
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ace-ie.min.css"/>
  <![endif]-->

  <!-- ace settings handler -->
  <script type="text/javascript" src="${ctx}/resource/js/ace-extra.min.js"></script>

  <!-- inline styles related to this page -->
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script type="text/javascript" src="${ctx}/resource/js/html5shiv.js"></script>
  <script type="text/javascript" src="${ctx}/resource/js/respond.min.js"></script>
  <![endif]-->

  <!-- customed -->
  <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/gamesvr.min.css
    ">

  <title>用户列表 - 游戏服务管理平台</title>
</head>
<body>
<div class="navbar navbar-default" id="navbar">
  <script type="text/javascript">
    try{ace.settings.check('navbar' , 'fixed')}catch(e){}
  </script>
  <jsp:include page="../../shared/_header.jsp"></jsp:include>
</div>
<div class="main-container" id="main-container">
  <script type="text/javascript">
    try{ace.settings.check('main-container' , 'fixed')}catch(e){}
  </script>

  <div class="main-container-inner">
    <div class="sidebar" id="sidebar">
      <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
      </script>
      <jsp:include page="../../shared/_menu.jsp">
        <jsp:param value="41" name="menu"/>
      </jsp:include>
      <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
      </script>
    </div>

    <div class="main-content">
      <div class="breadcrumbs" id="breadcrumbs">
        <%--上面一行空白，是否可用来显示滚动通知呢？--%>
      </div>

      <div class="page-content">

      </div>
    </div>
  </div>

  <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
  </a>
</div>

<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript" src="${ctx}/resource/js/jquery-2.0.3.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript" src="${ctx}/resource/js/jquery-1.10.2.min.js"></script>
<![endif]-->

<script type="text/javascript">
  if ("ontouchend" in document) document.write("<script src='${ctx}/resource/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>

<script type="text/javascript" src="${ctx}/resource/js/bootstrap.min.js"></script>

<!-- ace scripts -->
<script type="text/javascript" src="${ctx}/resource/js/ace-elements.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/ace.min.js"></script>

<script type="text/javascript">
  $(function(){

  });
</script>
</body>
</html>
