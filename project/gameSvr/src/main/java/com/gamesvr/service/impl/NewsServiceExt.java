package com.gamesvr.service.impl;

import com.gamesvr.aop.OperServiceLog;
import com.gamesvr.po.NewsExt;
import org.apache.log4j.Logger;

import com.gamesvr.service.INewsServiceExt;

public class NewsServiceExt extends NewsService implements INewsServiceExt {

    private static final Logger logger = Logger.getLogger(NewsServiceExt.class);

    @Override
    @OperServiceLog(description = "发布新闻", code = 401)
    public NewsExt announce(NewsExt newsExt) {
        return this.create(newsExt);
    }

}