package com.gamesvr.controller;

import com.gamesvr.framework.mybatis.EqCondition;
import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.session.SessionStatus;
import com.gamesvr.po.SysUserExt;
import com.gamesvr.service.ISysUserServiceExt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiguoqiang on 15/5/13.
 */
@Controller
@RequestMapping(value = "/game")
public class GameController {

    private static SessionStatus sessionStatus = SessionStatus.getInstance();

    private static final Logger logger = Logger.getLogger(GameController.class);

    @Autowired
    private ISysUserServiceExt sysUserService;

    @RequestMapping(value = "/index")
    public String index(ModelMap model) {
        return "/game/list";
    }

    @RequestMapping(value = "/goCreate")
    public String goCreate(ModelMap model) {
        return "/game/create";
    }

}
