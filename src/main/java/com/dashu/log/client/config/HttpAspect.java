package com.dashu.log.client.config;

import com.dashu.log.client.ClientUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/24 下午3:38
 **/
@Aspect
@Component
public class HttpAspect {
    @Resource
    private ClientUtil clientUtil;
    private Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut(value = "execution(public * com.dashu.log.client.controller..*(..)) && !execution(public * com.dashu.log.client.controller.UserController..*(..)))")
    public void pointcut(){}


    @Around("pointcut() ")
    public Object logincheck(ProceedingJoinPoint pjp){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (isSessionExist(request)){
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                return clientUtil.responseMessage(501,throwable.getMessage(),null);
            }
        }else {
            return clientUtil.responseMessage(501,"user not login",null);
        }

    }

    /**判断session是否存在**/
    public boolean isSessionExist(HttpServletRequest request){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        String sessionKey = clientUtil.getCookie(request,"token");
        if (session.getAttribute(sessionKey) == null){
            return false;
        }else {
            return true;
        }
    }

}
