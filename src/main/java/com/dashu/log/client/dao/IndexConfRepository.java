package com.dashu.log.client.dao;

import com.dashu.log.entity.IndexConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/11/27 下午3:36
 **/
public interface IndexConfRepository extends JpaRepository<IndexConf,Long> {

    /** 删除单条index conf **/
    @Transactional
    @Modifying
    @Query(value = "delete from index_conf where id = ?1",nativeQuery = true)
    void deleteSingleIndexConfById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update index_conf as t set t.scan_time = ?1 where t.index = ?2",nativeQuery = true)
    void updateScanTime(String scantime,String index);

    /**
     * update index total num
     * @param totleNum
     * @param index
     */
    @Transactional
    @Modifying
    @Query(value = "update index_conf as t set t.index_total = ?1 where t.index = ?2",nativeQuery = true)
    void updateIndexTotalNum(String totleNum,String index);
    /**
     * get all index conf
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "select t.id,t.index_name,t.filed,t.keywords,t.index_total,t.scan_interval,t.scan_time from index_conf as t",nativeQuery = true)
    List<IndexConf> getAllIndexConf();
    /**
     * add index conf
     * @param index
     * @param filed
     * @param keywords
     */
    @Transactional
    @Modifying
    @Query(value = "insert into index_conf(index_name,filed,keywords) values (?1,?2,?3)",nativeQuery = true)
    void addIndexConf(String index,String filed,String keywords);
}
