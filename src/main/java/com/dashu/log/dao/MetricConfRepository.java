package com.dashu.log.dao;

import com.dashu.log.entity.MetricConf;
import javafx.scene.chart.ValueAxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/12 下午1:50
 **/
public interface MetricConfRepository extends JpaRepository <MetricConf,Long>{

    /** 获取单个配置信息 **/
    @Query(value = "select t.metric_name,t.id,t.alter_level,t.alter_way,t.charge_man,t.frequency,t.is_show,t.metric_threshold from metric_conf as t where alter_name = ?1",nativeQuery = true)
    MetricConf getSingleConf(String altername);


    /** 获取全部配置 **/
    @Query(value = "select t.metric_name, t.id,t.alter_level,t.alter_way,t.charge_man,t.frequency,t.is_show,t.metric_threshold from metric_conf as t",nativeQuery = true)
    List<MetricConf> getAllMetricConf();


    /** 添加单条配置 **/
    @Transactional
    @Modifying
    @Query(value = "insert into metric_conf(metric_name,alter_level,alter_way,charge_man,frequency,is_show,metric_threshold) values (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void addSingleConf(String metricName,String alterLevel,String alterWay,String chargeMan,Integer frequency,Integer isShow,Integer metricThreshold);


    /** 删除单条配置 **/
    @Transactional
    @Modifying
    @Query(value = "delete from metric_conf where id = ?1",nativeQuery = true)
    void deleteSingleConfById(Integer id);
}
