package com.dashu.log.client.dao;

import com.dashu.log.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午1:59
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    /** 通过username获取相关信息 **/
    @Query(value = "select t.id,t.username,t.password,t.level,t.department from user as t where t.username = ?1",nativeQuery = true)
    User getUserByUsername(String username);
}
