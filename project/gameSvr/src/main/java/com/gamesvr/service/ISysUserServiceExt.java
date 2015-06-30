package com.gamesvr.service;

import com.gamesvr.framework.util.JsonResult;
import com.gamesvr.po.SysUserExt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ISysUserServiceExt extends ISysUserService {

    public JsonResult login(String loginName, String loginPwd, HttpServletRequest request);

    public SysUserExt register(SysUserExt sysUserExt, HttpServletRequest request);

    public SysUserExt uploadAvatar(String filePath, String sysUserId);

    public SysUserExt updateProfile(SysUserExt sysUserExt, int updateType);
}
