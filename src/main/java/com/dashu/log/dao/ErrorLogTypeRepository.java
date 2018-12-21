package com.dashu.log.dao;

import com.dashu.log.entity.ErrorLogTypeBak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/8/29 上午11:06
 **/
public interface ErrorLogTypeRepository extends JpaRepository<ErrorLogTypeBak,Long> {




    @Modifying
    @Transactional
    @Query(value = "update ErrorLogTypeBak e set e.message=?1 where e.id=?2")
    void updateMessage(String message,Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into error_log_type(business_id,business_name,log_level,category,keywords,message,host_name) values (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void addNewErrorLogType(String business_id,String business_name,String log_level,String category,String keywords,String message,String hostname);
}
