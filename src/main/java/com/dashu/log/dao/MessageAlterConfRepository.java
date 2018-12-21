package com.dashu.log.dao;

import com.dashu.log.entity.MessageAlterConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/13 上午10:10
 **/
public interface MessageAlterConfRepository extends JpaRepository<MessageAlterConf,Long> {

    /** 获取单条conf **/
    @Query(value = "select t.id, t.message_alter_name,t.alter_way,t.charge_man,t.is_show,t.alter_level,t.frequency from message_alter_conf as t where t.message_alter_name = ?1",nativeQuery = true)
    MessageAlterConf getSingleConf(String messageAlterName);

    /** 获取全部conf **/
    @Query(value = "select t.id, t.message_alter_name,t.alter_way,t.charge_man,t.is_show,t.alter_level,t.frequency from message_alter_conf as t",nativeQuery = true)
    List<MessageAlterConf> getAllConf();

    /** 添加一条conf **/
    @Transactional
    @Modifying
    @Query(value = "insert into message_alter_conf(message_alter_name,alter_way,charge_man,is_show,alter_level,frequency) values (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    void addSingleConf(String messageAlterName,String AlterWay,String chargeMan,Integer isShow,String alterLevel,Integer frequency);

    // TODO: 2018/12/19 更新单条conf
//    void updateSingleConfById();

    /** 删除单条conf **/
    @Transactional
    @Modifying
    @Query(value = "delete from message_alter_conf where id = ?1",nativeQuery = true)
    void deleteSingleConfById(Integer id);

}
