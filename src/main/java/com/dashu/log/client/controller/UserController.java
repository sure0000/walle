package com.dashu.log.client.controller;

import com.dashu.log.client.ClientUtil;
import com.dashu.log.client.service.UserService;
import com.dashu.log.entity.User;
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

    /** 登陆 **/
    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public User login(@RequestParam(value = "username")String username,
                      @RequestParam(value = "password")String password,
                      HttpServletResponse response,
                      HttpSession session) throws Exception {
        User user = userService.checkLogin(username,password);
        if (user!=null){
            session.setAttribute(username,user);
            String id =session.getId();
            clientUtil.writeCookie(response,"token",username);
        }
        return user;
    }
}
