package com.dashu.log.client.service;

import com.dashu.log.client.dao.UserRepository;
import com.dashu.log.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午2:10
 **/

public interface UserService {

    /** 登陆服务 **/
    User checkLogin(String username,String password);
}
