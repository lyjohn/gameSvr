package com.gamesvr.controller;

import com.gamesvr.aop.OperControllerLog;
import com.gamesvr.framework.mybatis.EqCondition;
import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.session.SessionStatus;
import com.gamesvr.framework.util.Constants;
import com.gamesvr.framework.util.JsonResult;
import com.gamesvr.po.SysUserExt;
import com.gamesvr.service.ISysUserServiceExt;
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
@RequestMapping(value = "/admin")
public class AdminController {

    private static SessionStatus sessionStatus = SessionStatus.getInstance();

    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private ISysUserServiceExt sysUserService;

    @RequestMapping(value = "/user/list")
    public String userList(ModelMap model) {
        return "/admin/user/index";
    }

    @RequestMapping(value = "/oper/list")
    public String operList(ModelMap model) {
        return "/admin/oper/index";
    }

    @RequestMapping(value = "/admin/checkLoginName")
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
