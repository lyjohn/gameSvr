package com.gamesvr.controller;

import com.gamesvr.framework.session.SessionStatus;
import com.gamesvr.service.ISysUserServiceExt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by laiguoqiang on 15/5/13.
 */
@Controller
@RequestMapping(value = "/server")
public class ServerController {

    private static SessionStatus sessionStatus = SessionStatus.getInstance();

    private static final Logger logger = Logger.getLogger(ServerController.class);

    @Autowired
    private ISysUserServiceExt sysUserService;

    @RequestMapping(value = "/index")
    public String index(ModelMap model) {
        return "/server/list";
    }

    @RequestMapping(value = "/goCreate")
    public String goCreate(ModelMap model) {
        return "/server/create";
    }

}
