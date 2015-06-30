<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/core/include.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/font-awesome.min.css">

    <!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->
    <!-- ace styles -->
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ace.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ace-rtl.min.css">

    <!--[if lte IE 8]>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resource/js/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/resource/js/respond.min.js"></script>
    <![endif]-->

    <!-- customed -->
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/gamesvr.min.css
    ">

    <title>登录 - 游戏服务管理平台</title>
</head>
<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i>
                            <span class="red">Game</span>
                            <span class="white">服务器管理平台</span>
                        </h1>
                        <h4 class="blue">&copy; 公司名称</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        请输入您的用户名和密码
                                    </h4>

                                    <div class="space-6"></div>
                                    <form id="loginForm">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" id="login_username" name="login_username" class="form-control" placeholder="用户名"/>
															<i class="icon-user"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="login_password" name="login_password" class="form-control"
                                                                   placeholder="密码"/>
															<i class="icon-lock"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>

                                                <button type="button"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    登录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                    <div class="login-note">
                                    </div>
                                </div>
                                <!-- /widget-main -->

                                <div class="toolbar clearfix">
                                    <div class="toolbar-note">
                                        <span>注册或忘记密码，请联系管理员.</span>
                                    </div>
                                </div>
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <!-- /login-box -->
                    </div>
                    <!-- /position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- /.main-container -->

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

<script type="text/javascript" src="${ctx}/resource/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/layer/layer.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">

    $(function(){
        var validatorLogin = $('#loginForm').validate({
            errorLabelContainer: ".login-note",
            focusInvalid: false,
            focusCleanup: true, //移进来，把错误消掉
            onsubmit: false,
            onfocusout: function (element) { $(element).valid();},
            rules: {
                "login_username": {
                    required: true
                },
                "login_password":{
                    required: true,
                    minlength: 6
                }
            },
            messages: {
                "login_username": {
                    required: "用户名不能为空"
                },
                "login_password":{
                    required: "密码不能为空",
                    minlength: "密码不能小于6位"
                }
            },
            highlight: function (element) {
                $(".login-note").fadeIn();
            },
            success: function (label) {
                $(".login-note").fadeOut();
                label.remove();
            }
        });

        $(document).on("click","form button",function(){
            var isSuc = validatorLogin.form();
            if (!isSuc) {
                layer.msg("有错误项，还不能登录", {icon: 5, offset: '110px'});
                return false;
            }

            var loginName = $("#login_username").val();
            var loginPwd = $("#login_password").val();
            var rm = $("form:checkbox").prop("checked");
            if(rm){//记住我
                var date=new Date();
                date.setTime(date.getTime+7*24*3600*1000);
                document.cookie="username="+loginName+"; expires="+date.toGMTString();
            }else{
                var date=new Date();
                date.setTime(date.getTime()-10000);
                document.cookie="username=v; expires="+date.toGMTString();
            }

            $.post("${ctx}/doSignIn.do", {loginName: loginName, loginPwd: loginPwd}, function (res) {
                if (res.status == 0) {
                    window.location.href = "${ctx}/index"
                } else {
                    layer.msg(res.message, {icon: 5, offset: '110px'});
                }
            }, "json");
        })
    });

</script>
</body>
</html>
