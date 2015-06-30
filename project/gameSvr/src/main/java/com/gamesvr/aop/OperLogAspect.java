package com.gamesvr.aop;

import com.gamesvr.framework.util.Constants;
import com.gamesvr.framework.util.JSONUtil;
import com.gamesvr.framework.util.JsonResult;
import com.gamesvr.po.*;
import com.gamesvr.service.IOperLogServiceExt;
import com.gamesvr.service.impl.OperLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by laiguoqiang on 15/5/23.
 */
@Component
@Aspect
public class OperLogAspect {

    //注入Service用于把日志保存数据库
    private IOperLogServiceExt operLogService;

    public IOperLogServiceExt getOperLogService() {
        return operLogService;
    }

    public void setOperLogService(IOperLogServiceExt operLogService) {
        this.operLogService = operLogService;
    }

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(OperLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(com.gamesvr.aop.OperServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.gamesvr.aop.OperControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作，系统只拦截用户登录
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            Integer code = getControllerMethodCode(joinPoint);
            String desc = getControllerMethodDescription(joinPoint);

            OperLogExt operLogExt = new OperLogExt();
            operLogExt.setLogTime(new Date());
            operLogExt.setUserIp(ip);
            if (sessionUser == null) {
                operLogExt.setUserName("匿名用户");
            } else {
                operLogExt.setUserName(sessionUser.getUserName());
            }

            operLogExt.setLogDesc(desc);
            operLogExt.setLogAction(code);

            if (code == 102) {
                operLogExt.setLogObjId(0L);

                String url = "http://" + request.getServerName() //服务器地址
                        + ":"
                        + request.getServerPort()           //端口号
                        + request.getContextPath()      //项目名称
                        + request.getServletPath()      //请求页面或其他地址
                        + "?" + (request.getQueryString()); //参数
                operLogExt.setLogContent("Url路径: " + url + ", Controller方法:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            } else {
                operLogExt.setLogObjId(-1L);
                operLogExt.setLogContent("");
            }
            //保存数据库
            operLogService.create(operLogExt);
        } catch (Exception e) {
            logger.error("AOP Controller Before :{}", e.getMessage());
        }
    }

    /*
    * Service发生异常了就记录下来，以便后续解决，例如每天定时提醒开发人员有哪些异常了
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {

                //以Http开头的参数 不存储 例如 HttpServletRequest HttpServletResponse HttpSession 数据太长了
                if (joinPoint.getArgs()[i].getClass() == request.getClass() || joinPoint.getArgs()[i].getClass() == session.getClass())
                    continue;

                if (joinPoint.getArgs()[i].getClass() == String.class || joinPoint.getArgs()[i].getClass() == Long.class || joinPoint.getArgs()[i].getClass() == Integer.class) {
                    params += joinPoint.getArgs()[i] + ";";
                } else {
                    params += JSONUtil.object2JsonString(joinPoint.getArgs()[i]) + ";";
                }
            }
        }

         /*==========记录本地异常日志==========*/
        logger.error("AOP Service AfterThrown: 异常方法:{}\n异常代码:{}\n异常信息:{}\n参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
    }

    /**
     * 配置后置返回通知,使用在方法aspect()上注册的切入点
     *
     * @param joinPoint
     */
    @AfterReturning(value = "serviceAspect()", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        SysUserExt sessionUser = (SysUserExt) session.getAttribute(Constants.SESSION_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();

        try {
            String desc = getServiceMethodDescription(joinPoint);
            Integer code = getServiceMethodCode(joinPoint);

            OperLogExt operLogExt = new OperLogExt();
            operLogExt.setLogTime(new Date());
            operLogExt.setUserIp(ip);
            if (sessionUser != null)
                operLogExt.setUserName(sessionUser.getUserName());
            else {
                operLogExt.setUserName("匿名用户");
            }

            operLogExt.setLogDesc(desc);
            operLogExt.setLogAction(code);

            if (code == 101) {//登录系统,第一个参数是用户名
                operLogExt.setUserName(joinPoint.getArgs()[0].toString());

                JsonResult loginResult = (JsonResult) returnValue;
                int loginStatus = loginResult.getStatus();
                if (loginStatus == 1) {
                    operLogExt.setLogContent("系统用户登录成功");
                    operLogExt.setLogObjId(0L);
                } else if (loginStatus == 2) {
                    operLogExt.setLogContent("活动用户登录成功");
                    operLogExt.setLogObjId(0L);
                } else {
                    operLogExt.setLogContent("登录的未知情况");
                    operLogExt.setLogObjId(-1L);
                }
            } else if (code == 103) {//注册系统用户
                SysUserExt sysUserExt = (SysUserExt) joinPoint.getArgs()[0];

                operLogExt.setUserName(sysUserExt.getLoginName());
                operLogExt.setLogObjId(0L);
                operLogExt.setLogContent(JSONUtil.object2JsonString(operLogExt));
            }else if(code == 104){ //系统用户绑定活动用户
                operLogExt.setUserName(sessionUser.getUserName());
                operLogExt.setLogObjId(0L);

                JsonResult bindResult = (JsonResult)returnValue;
                if(bindResult.getStatus()==0)
                    operLogExt.setLogContent("关联活动用户:"+joinPoint.getArgs()[0]+" 成功");
                else
                    operLogExt.setLogContent("关联活动用户:"+joinPoint.getArgs()[0]+" 失败:"+bindResult.getMessage());
            }else if(code==105){//活动用户关联系统用户
                operLogExt.setUserName(sessionUser.getUserName());

                JsonResult bindResult = (JsonResult)returnValue;
                if(bindResult.getStatus()==0)
                    operLogExt.setLogContent("关联系统用户:"+joinPoint.getArgs()[0]+" 成功");
                else
                    operLogExt.setLogContent("关联系统用户:"+joinPoint.getArgs()[0]+" 失败:"+bindResult.getMessage());
            }else if(code==106){//编辑系统用户基本信息
                SysUserExt sysUserExt = (SysUserExt) joinPoint.getArgs()[0];

                operLogExt.setUserName(sessionUser.getUserName());
                operLogExt.setLogObjId(0L);
                operLogExt.setLogContent(JSONUtil.object2JsonString(sysUserExt));
            }else if(code == 401){
                NewsExt newsExt = (NewsExt)returnValue;

                operLogExt.setLogObjId(newsExt.getId());
                operLogExt.setLogContent(JSONUtil.object2JsonString(newsExt));
            }else {
                operLogExt.setLogObjId(-1L);
                operLogExt.setLogContent("未处理的日志信息"+code+"，请开发人员知悉");
            }
            //保存数据库
            operLogService.create(operLogExt);
        } catch (Exception ex)
        {
            //记录本地异常日志
            logger.error("AOP Service AfterReturn 异常信息:{}", ex.getMessage());
            ex.getStackTrace();
        }

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(OperServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的Code信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Integer getServiceMethodCode(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        Integer code = 0;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    code = method.getAnnotation(OperServiceLog.class).code();
                    break;
                }
            }
        }
        return code;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(OperControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的Code信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Integer getControllerMethodCode(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        Integer code = 0;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    code = method.getAnnotation(OperControllerLog.class).code();
                    break;
                }
            }
        }
        return code;
    }
}
