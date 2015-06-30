package com.gamesvr.service.impl;

import com.gamesvr.aop.OperServiceLog;
import com.gamesvr.framework.mybatis.EqCondition;
import com.gamesvr.framework.mybatis.ICondition;
import com.gamesvr.framework.util.Constants;
import com.gamesvr.framework.util.FormatUtils;
import com.gamesvr.framework.util.JsonResult;
import com.gamesvr.framework.util.MD5Util;
import com.gamesvr.po.SysUserExt;
import org.apache.log4j.Logger;

import com.gamesvr.service.ISysUserServiceExt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SysUserServiceExt extends SysUserService implements ISysUserServiceExt {

    private static final Logger logger = Logger.getLogger(SysUserServiceExt.class);

    @Override
    @OperServiceLog(description = "登录系统", code = 101)
    public JsonResult login(String loginName, String loginPwd, HttpServletRequest request) {
        JsonResult result = new JsonResult();

        List<ICondition> conditions = new ArrayList<ICondition>();
        conditions.add(new EqCondition("loginName", loginName));

        List<SysUserExt> userList = this.criteriaQuery(conditions);
        if (userList != null && userList.size() == 1) {
            SysUserExt sysUser = (SysUserExt) userList.get(0);
            if (MD5Util.MD5(loginPwd).equals(sysUser.getLoginPwd())) {

                Date now = new Date();
                String ipAddress = FormatUtils.getIpAddress(request);
                sysUser.setLastLoginTime(now);
                sysUser.setLastLoginIP(ipAddress);
                this.update(sysUser);

                result.setData(sysUser);
                result.setStatus(1);
            } else {
                result.setStatus(5);
            }
        } else {
            result.setStatus(3);
        }

        return result;
    }

    @Override
    @OperServiceLog(description = "创建用户", code = 103)
    public SysUserExt register(SysUserExt sysUserExt, HttpServletRequest request) {
        //用户ID生成
        sysUserExt.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        //密码加密
        sysUserExt.setLoginPwd(MD5Util.MD5(sysUserExt.getLoginPwd()));

        //如果用户注册的时候没有填写昵称  则默认为登录名
        if (FormatUtils.isEmpty(sysUserExt.getUserName())) {
            sysUserExt.setUserName(sysUserExt.getLoginName());
        }

        Date now = new Date();
        String ipAddress = FormatUtils.getIpAddress(request);

        sysUserExt.setLastLoginIP(ipAddress);
        sysUserExt.setLastLoginTime(now);

        return this.create(sysUserExt);
    }

    @Override
    @OperServiceLog(description = "上传用户头像头像",code = 108)
    public SysUserExt uploadAvatar(String filePath, String sysUserId) {
        SysUserExt sysUserExt = this.load(sysUserId);

        if (sysUserExt == null)
            return null;

        sysUserExt.setUserAvatar(filePath);
        this.update(sysUserExt);

        return sysUserExt;
    }

    /**
     * 修改用户基本in洗
     * @param sysUserExt 用户实体，承载需要变更的内容，某些项是空的
     * @param updateType 更新类型 1:基本信息(username remark) 2:通讯录(email tel)  3:密码)
     * @return
     */
    @Override
    @OperServiceLog(description = "编辑了个人资料",code = 106)
    public SysUserExt updateProfile(SysUserExt sysUserExt,int updateType) {
        SysUserExt sysUserExtPer = this.load(sysUserExt.getId());
        if (sysUserExt == null)
            return null;

        if(updateType == 1){
            sysUserExtPer.setUserName(sysUserExt.getUserName());
            sysUserExtPer.setUserRemark(sysUserExt.getUserRemark());
        }else if(updateType == 2){
            sysUserExtPer.setEmail(sysUserExt.getEmail());
            sysUserExtPer.setTel(sysUserExt.getTel());
        }else if(updateType == 3){
            sysUserExtPer.setLoginPwd(sysUserExt.getLoginPwd());
        }else
            return sysUserExtPer;

        this.update(sysUserExtPer);

        return sysUserExtPer;
    }
}