package com.gamesvr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by laiguoqiang on 15/5/13.
 */
@Controller
@RequestMapping(value = "/errors")
public class ErrorController {

    @RequestMapping(value = "/error/{id}")
    public String index(@PathVariable("id") int id,ModelMap model) {

        String message = "";
        switch (id){
            case 1:
                message = "用户未登录";
                break;
            case 2:
                message = "您没有权限";
                break;
            default:
                message = "系统错误，程序猿加班改BUG~";
                break;
        }
        
        model.addAttribute("message",message);
        return "/errors/error";
    }

}
