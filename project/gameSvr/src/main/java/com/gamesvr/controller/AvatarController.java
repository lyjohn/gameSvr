package com.gamesvr.controller;

import com.gamesvr.framework.session.SessionStatus;
import com.gamesvr.framework.util.*;
import com.gamesvr.po.*;
import com.gamesvr.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * Created by laiguoqiang on 15/5/13.
 */
@Controller
@RequestMapping(value = "/avatar")
public class AvatarController {

    private static SessionStatus sessionStatus = SessionStatus.getInstance();

    private static final Logger logger = Logger.getLogger(AvatarController.class);

    @Autowired
    private ISysUserServiceExt sysUserService;

    @Autowired
    private IAvatarService avatarService;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadAvatar(@RequestParam(value = "file", required = true) MultipartFile file, @RequestParam(value = "type", required = true) int type, HttpServletRequest request, HttpSession session) {
        JsonResult result = new JsonResult();
        try {
            String fileName = file.getOriginalFilename();

            String[] str = {".jpg", ".jpeg", ".bmp", ".gif", ".png", ".JPG", ".JPEG", ".BMP", ".GIF", ".PNG"};

            boolean isPic = false;
            for (String s : str) {
                if (fileName.endsWith(s)) {
                    isPic = true;
                    break;
                }
            }

            if (!isPic)
                throw new Exception("上传的文件不是有效图片: jpg jpeg bmp gif png");

            //上传头像
            String filePath = avatarService.doAvatarSave(request, file, type);

            result.setData(filePath);
            result.setStatus(0);
        } catch (Exception e) {
            result.setStatus(1);
            result.setMessage(e.getMessage());
        }
        String str = JSONUtil.object2JsonString(result);

        return str;
    }

    //type: 1:用户 2:活动 3:小组
    @RequestMapping(value = "/cut")
    @ResponseBody
    public JsonResult cutAvatar(@RequestParam(value = "filePath", required = true) String filePath, @RequestParam(value = "x1", required = true) int x1, @RequestParam(value = "y1", required = true) int y1, @RequestParam(value = "x2", required = true) int x2, @RequestParam(value = "y2", required = true) int y2, HttpServletRequest request, HttpSession session) {
        JsonResult result = new JsonResult();
        try {
            int width = x2 - x1;
            int height = y2 - y1;
            //剪切头像
            String savePath = avatarService.doAvatarCut(request, session, filePath, x1, y1, width, height);

            SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
            sysUserService.uploadAvatar(savePath, sessionUser.getId());

            result.setData("resource" + File.separator + savePath);
            result.setStatus(0);
        } catch (Exception e) {
            result.setStatus(1);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    /**
     * 前台页面显示图片
     *
     * @param id       用户的ID
     * @param response 返回
     * @return
     */
    @RequestMapping(value = "/user/{id}")
    public void getUserAvatar(@PathVariable("id") String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            String avatarPath = "";
            SysUserExt sysUserExt = sysUserService.load(id);
            if (sysUserExt != null)
                avatarPath = sysUserExt.getUserAvatar();

            if (FormatUtils.isEmpty(avatarPath))
                avatarPath = "avatar" + File.separator + "default" + File.separator + "user.png";

            avatarService.doAvatarShow(response, avatarPath);
        } catch (Exception e) {
        }
    }
}
