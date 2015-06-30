package com.gamesvr.controller;

import com.gamesvr.aop.OperControllerLog;
import com.gamesvr.framework.mybatis.EqCondition;
import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.session.SessionStatus;
import com.gamesvr.framework.util.*;
import com.gamesvr.po.SysUserExt;
import com.gamesvr.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiguoqiang on 15/5/13.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    private static SessionStatus sessionStatus = SessionStatus.getInstance();

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private ISysUserServiceExt sysUserService;

    @RequestMapping(value = {"/","/index"})
    public String index(ModelMap model) {
        return "/user/index";
    }

    @RequestMapping(value ="/goSignIn")
    public String goSignIn(){
        return "/login";
    }

    @RequestMapping(value = "/doSignIn")
    @ResponseBody
    public JsonResult doSignIn(@RequestParam(value="loginName",required=true) String loginName, @RequestParam(value="loginPwd",required=true) String loginPwd, HttpServletRequest request, HttpSession session){
        JsonResult result = new JsonResult();
        try{
            JsonResult loginResult = null;

            loginResult = sysUserService.login(loginName,loginPwd,request);

            if(loginResult.getStatus() == 1 || loginResult.getStatus() == 2) {
                sessionStatus.checkAndLogin(session,(SysUserExt)loginResult.getData());
                result.setData(loginResult.getStatus());
                result.setStatus(0);
            }else {
                result.setMessage(Constants.LOGIN_RESULT_MAP.get(loginResult.getStatus()));
            }
        }catch (Exception ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/doSignOut")
    @OperControllerLog(description = "退出系统", code = 102)
    public String doLogout(HttpSession session,ModelMap model){
        try{
            sessionStatus.logout(session);

        }catch (Exception ex){
            logger.error(ex.getStackTrace());
        }

        return "redirect:/goSignIn.do";
    }

    @RequestMapping(value = "/checkLoginName")
    @ResponseBody
    public boolean checkUser(@RequestParam(value="loginName",required=true) String loginName){
        boolean result;
        try{
            List<ICondition> conditions = new ArrayList<ICondition>();
            conditions.add(new EqCondition("loginName", loginName));
            List<SysUserExt> userList = sysUserService.criteriaQuery(conditions);
            if(userList.size() > 0)
                result = false;
            else
                result = true;
        }catch (Exception ex){
            result = false;
        }
        return result;
    }

}
