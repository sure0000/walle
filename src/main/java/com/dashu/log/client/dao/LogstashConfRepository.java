package com.dashu.log.client.dao;

import com.dashu.log.entity.LogstashConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/11/27 下午3:44
 **/
public interface LogstashConfRepository extends JpaRepository<LogstashConf,Long> {

    /**
     * get all hostnam
     * @return
     */
    @Query(value = "select hostname from logstash_conf",nativeQuery = true)
    List<String> getAllHostanme();


    /**
     * add logstash host
     * @param hostname
     */
    @Transactional
    @Modifying
    @Query(value = "insert into logstash_conf(hostname) values (?1)",nativeQuery = true)
    void addHostname(String hostname);
}
