package com.gamesvr.controller;

/**
 * Created by YangJunLin on 2015/4/18.
 */


import com.gamesvr.framework.mybatis.EqCondition;
import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.InCondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.session.SessionStatus;
import com.gamesvr.framework.util.Constants;
import com.gamesvr.framework.util.FormatUtils;
import com.gamesvr.framework.util.JsonResult;
import com.gamesvr.framework.util.MD5Util;
import com.gamesvr.model.SysUserModel;

import com.gamesvr.po.*;
import com.gamesvr.service.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private static SessionStatus sessionStatus = SessionStatus.getInstance();

    @Autowired
    private ISysUserServiceExt sysUserService;

    @RequestMapping(value = {"/", "/index"})
    public String show(@ModelAttribute SysUserModel sysUserModel, ModelMap model, HttpSession session) throws IOException {
        SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);

        SysUserExt sysUserExt = sysUserService.load(sessionUser.getId());

        sysUserModel.setSysUserExt(sysUserExt);
        model.addAttribute("model", sysUserModel);

        return "/user/index";
    }

    @RequestMapping(value = "/profile")
    public String goSysUserProfile(@ModelAttribute SysUserModel sysUserModel, ModelMap model, HttpSession session) {
        SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);

        SysUserExt sysUserExt = sysUserService.load(sessionUser.getId());

        sysUserModel.setSysUserExt(sysUserExt);
        model.addAttribute("model", sysUserModel);

        return "/user/profile";
    }

    @RequestMapping(value = "/contact")
    @ResponseBody
    public JsonResult doContactSave(@ModelAttribute SysUserExt sysUserExt, ModelMap model, HttpSession session) {
        JsonResult result = new JsonResult();
        try {
            SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
            sysUserExt.setId(sessionUser.getId());

            sysUserService.updateProfile(sysUserExt, 2);

            result.setStatus(0);
        } catch (Exception ex) {
            logger.trace(ex);
            result.setMessage("保存失败");
        }
        return result;
    }

    @RequestMapping(value = "/intro")
    @ResponseBody
    public JsonResult doIntroSave(@ModelAttribute SysUserExt sysUserExt, ModelMap model, HttpSession session) {
        JsonResult result = new JsonResult();
        try {
            SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
            sysUserExt.setId(sessionUser.getId());

            sysUserService.updateProfile(sysUserExt, 1);

            result.setStatus(0);
        } catch (Exception ex) {
            logger.trace(ex);
            result.setMessage("保存失败");
        }
        return result;
    }

    @RequestMapping(value = "/setpwd")
    @ResponseBody
    public JsonResult doPwdSave(@RequestParam(value = "oldPwd", required = true) String oldPwd, @RequestParam(value = "newPwd", required = true) String newPwd, HttpSession session) {
        JsonResult result = new JsonResult();
        try {
            SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);

            SysUserExt sysUserExt = sysUserService.load(sessionUser.getId());

            if (MD5Util.MD5(oldPwd).equals(sysUserExt.getLoginPwd())) {
                sysUserExt.setLoginPwd(MD5Util.MD5(newPwd));
                sysUserService.updateProfile(sysUserExt, 3);

                result.setStatus(0);
                result.setMessage("修改密码成功");
            } else {
                result.setMessage("旧密码不不正确");
            }
        } catch (Exception ex) {
            logger.trace(ex);
            result.setMessage("保存失败");
        }
        return result;
    }

//    @RequestMapping(value="/users/{userId}")：{×××}占位符，  请求的 URL 可以是  “/users/123456”或
//    “/users/abcd” ，通过 6.6.5 讲的通过@PathV ariable 可以提取 URI 模板模式中的{×××}中的×××变量。
//    @RequestMapping(value="/users/{userId}/create") ： 这样 也 是 可 以 的 ，请 求的 URL 可 以是
//    “/users/123/create” 。
//    @RequestMapping(value="/users/{userId}/topics/{topicId}")：这样也是可以的，请求的 URL 可以是
//    “/users/123/topics/123”


    @RequestMapping(value = "/{id}")
    public String getUser(@PathVariable("id") String id, @ModelAttribute SysUserModel sysUserModel, ModelMap model, HttpSession session) {

        SysUserExt sysUserExt = null;
        if (FormatUtils.isEmpty(id)) {
            SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
            sysUserExt = sysUserService.load(sessionUser.getId());

        } else {
            sysUserExt = sysUserService.load(id);
        }

        sysUserModel.setSysUserExt(sysUserExt);
        model.addAttribute("model", sysUserModel);
        return "/user/index";
    }

}
