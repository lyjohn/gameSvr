package com.gamesvr.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface IAvatarService{

    String doAvatarSave(HttpServletRequest request, MultipartFile file, int type);

    String doAvatarCut(HttpServletRequest request, HttpSession session, String filePath, int x, int y, int width, int height) throws IOException;

    /**
     * 显示头像图片
     * @param response
     * @param filePath 图片的存储位置
     * @return
     */
    void doAvatarShow(HttpServletResponse response, String filePath);
}
