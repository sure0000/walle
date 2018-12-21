package com.dashu.log.dao;

import com.dashu.log.entity.SelfAlterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午2:49
 **/
public interface SelfAlterHistoryRepository extends JpaRepository<SelfAlterHistory,Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into self_alter_history(alter_name, alter_way, charge_man, is_show, content, alter_time, alter_type) values (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void addSingleAlter(String alterName,String alterWay,String chargeMan,Integer isShow,String content,Date alterTime,String alterType);

    @Query(value = "select t.id,t.alter_name,t.alter_type,t.alter_way,t.charge_man,t.content,t.is_show,t.is_handle,t.alter_time from self_alter_history as t where t.is_show = 1 order by t.alter_time desc ,t.is_handle asc ",nativeQuery = true)
    List<SelfAlterHistory> getAllAlter();

    @Transactional
    @Modifying
    @Query(value = "update self_alter_history set is_handle = 1 where id = ?1",nativeQuery = true)
    void updateAlterStatusById(Integer id);
}
