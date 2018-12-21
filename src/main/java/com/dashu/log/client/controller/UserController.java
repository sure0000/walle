package com.dashu.log.client.controller;

import com.dashu.log.client.service.UserService;
import com.dashu.log.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午2:06
 **/
@RestController
public class UserController {
    @Resource
    private UserService userService;

    /** 登陆 **/
    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public User login(@RequestParam(value = "username")String username,
                      @RequestParam(value = "password")String password){
        return userService.checkLogin(username,password);
    }
}
