package com.gamesvr.framework.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.gamesvr.framework.util.Constants;
import com.gamesvr.po.SysUser;

public class SessionStatus {

    private static SessionStatus instance = new SessionStatus();

    public static SessionStatus getInstance() {
        return instance;
    }

    private Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>(10000);

    private long sessionCount = 0;

    private SessionStatus() {
    }

    public synchronized long getSessionCount() {
        return sessionCount;
    }

    /**
     * 登录的时候调用，将用户的 id name avatar记录到Session中
     *
     * @param session HttpSession
     * @param user    用户对象
     * @return 返回值
     */
    public synchronized void checkAndLogin(HttpSession session, SysUser user) {
        SysUser sessionUser = (SysUser) session.getAttribute(Constants.SESSION_USER);
        String uid = String.valueOf(user.getId());

        if (sessionUser == null) {
            session.setAttribute(Constants.SESSION_USER, user);
            HttpSession s = sessionMap.get(user.getId());
            if(s == null){
                sessionMap.put(uid, session);
                sessionCount++;
            }else{
                try{
                    s.invalidate();
                }catch(Exception e){
                    sessionCount--;
                }
                sessionMap.put(uid, session);
                sessionCount++;
            }

        } else {
            sessionMap.remove(sessionUser.getId());
            session.setAttribute(Constants.SESSION_USER, user);
            sessionMap.put(uid, session);
        }
    }

    public synchronized void logout(HttpSession session) {
        SysUser user = (SysUser) session.getAttribute(Constants.SESSION_USER);
        if (user != null) {
            session.removeAttribute(Constants.SESSION_USER);
            HttpSession s = sessionMap.remove(user.getId());
            if(s != null){
                sessionCount--;
            }
        }
    }
}
