package com.gamesvr.controller;

/**
 * Created by LaiGuoqiang on 2015/5/14.
 */

import com.gamesvr.framework.mybatis.EqCondition;
import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.mybatis.Order;
import com.gamesvr.framework.util.Constants;
import com.gamesvr.framework.util.JsonResult;
import com.gamesvr.framework.util.Pagination;
import com.gamesvr.po.NewsExt;
import com.gamesvr.po.SysUserExt;
import com.gamesvr.service.INewsServiceExt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/news")
public class NewsController {

    private static final Logger logger = Logger.getLogger(NewsController.class);

    @Autowired
    private INewsServiceExt newsService;

    @RequestMapping(value = "/create")
    public String goCreate(){
        return "/news/createnews";
    }

    @RequestMapping(value = "/doCreate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doCreate(@ModelAttribute NewsExt newsExt, HttpSession session, HttpServletRequest request){
        JsonResult result = new JsonResult();
        try{

            SysUserExt sessionUser = (SysUserExt)session.getAttribute(Constants.SESSION_USER);
            newsExt.setCreateBy(sessionUser.getId());
            newsExt.setCreateTime(new Date());
            newsExt.setReadCount(0);
            newsService.announce(newsExt);

            result.setStatus(0);
            result.setData(newsExt);
        }catch (Exception ex){
            result.setMessage("服务器异常，请重新提交");
            logger.error(ex);
        }
        return result;
    }




    @RequestMapping(value= "/show")
    @ResponseBody
    public JsonResult goShow(HttpSession session){

        SysUserExt sessionUser = (SysUserExt)session.getAttribute(Constants.SESSION_USER);

//        Long partyId = sessionUser.getPartyId();
//
//        List<ICondition> conditions = new ArrayList<ICondition>();
//        conditions.add(new EqCondition("partyId",partyId));
//
//        List<Order> orders = new ArrayList<Order>();
//        orders.add(Order.desc("createTime"));
//
//        Pagination pp = new Pagination();
//        pp.setCurrentPage(1);
//        pp.setPageSize(5);
//
//        List<NewsExt> newsExts = newsService.criteriaQuery(conditions,orders,pp);

        NewsExt newsExt = newsService.load(1L);

        JsonResult result = new JsonResult();
        result.setData(newsExt);

        return result;
    }

}
