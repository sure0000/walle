package com.dashu.log.client.serviceImp;

import com.dashu.log.client.dao.UserRepository;
import com.dashu.log.client.service.UserService;
import com.dashu.log.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午2:17
 **/
@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public User checkLogin(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user == null){
            return null;
        }else if (!user.getPassword().equals(password)){
            return null;
        }else {
            return user;
        }
    }
}
