package com.gamesvr.service;

import com.gamesvr.po.NewsExt;

public interface INewsServiceExt extends INewsService{

    public NewsExt announce(NewsExt newsExt);
}
