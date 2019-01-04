package com.dashu.log.client.controller;

import com.dashu.log.client.ClientUtil;
import com.dashu.log.client.service.UserService;
import com.dashu.log.entity.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午2:06
 **/
@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private ClientUtil clientUtil;

    /**登出**/
    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpSession session){
        try {
            String sessionKey = clientUtil.getCookie(request,"token");
            session.removeAttribute(sessionKey);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(503,e.getMessage(),false);
        }
    }

    /** 登陆 **/
    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public String login(@RequestParam(value = "username")String username,
                            @RequestParam(value = "password")String password,
                            HttpServletResponse response,
                            HttpSession session) throws Exception {
        User user = userService.checkLogin(username,password);
        if (user!=null){
            int random = 0 + (int)(Math.random() * 10000);
            String sessionKey = clientUtil.getMD5Str(username + String.valueOf(random));
            session.setAttribute(sessionKey,user);
            clientUtil.writeCookie(response,"token",sessionKey);
            return clientUtil.responseMessage(200,sessionKey,user);
        }else{
            return clientUtil.responseMessage(201,"user is not exist or password is wrong!",null);
        }

    }

}
