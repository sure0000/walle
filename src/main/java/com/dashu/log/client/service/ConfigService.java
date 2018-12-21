package com.dashu.log.client.service;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/19 上午10:42
 **/
public interface ConfigService {

    boolean addSingleMessageConf(String messageAlterName,String AlterWay,String chargeMan,Integer isShow,String alterLevel,Integer frequency);

    boolean deleteSingleMessageConf(Integer id);

    boolean addSingleMetricConf(String metricName,String alterLevel,String alterWay,String chargeMan,Integer frequency,Integer isShow,Integer metricThreshold);

    boolean deleteSingleMetricConf(Integer id);
}
