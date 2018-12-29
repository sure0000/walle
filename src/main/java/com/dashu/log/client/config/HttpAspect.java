package com.dashu.log.client.config;

import com.dashu.log.client.ClientUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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


    @Pointcut(value = "execution(public * com.dashu.log.client.controller..*(..)) && !execution(public * com.dashu.log.client.controller.UserController.login(..)))")
    public void pointcut(){}

    @Before("pointcut()") //&& args(session,pjp)
    public Object dobefore(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        String token = clientUtil.getCookie(request,"token");
//        if (token == null){
//            return null;
//        }else {
//            if (session.getAttribute(token) != null){
//                try {
//                    pjp.proceed();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }
//        }

        return null;

    }

//    @After(value = "pointcut()")
//    public void doafter(){
//
//    }
}
