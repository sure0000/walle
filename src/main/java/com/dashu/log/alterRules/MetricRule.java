package com.dashu.log.alterRules;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.dao.MetricConfRepository;
import com.dashu.log.dao.SelfAlterHistoryRepository;
import com.dashu.log.entity.MetricConf;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/12 下午1:43
 **/
@Component
public class MetricRule {
    @Resource
    private MetricConfRepository metricConfRepository;
    @Resource
    private SelfAlterHistoryRepository selfAlterHistoryRepository;

    /**
     * 是否超过阈值
     */
    public void isExceed (String metricname,int value){
        MetricConf metricConf = metricConfRepository.getSingleConf(metricname);
        int metricThreshold = metricConf.getMetricThreshold();
        if (value > metricThreshold){
            selfAlterHistoryRepository.addSingleAlter(metricname,metricConf.getAlterWay(),metricConf.getChargeMan(),Integer.valueOf(metricConf.getIsShow()),String.valueOf(value),new Date(),"metric");
            WalleNotify walleNotify = new WalleNotify();
            walleNotify.sendMessage(metricname,String.valueOf(value),metricname);
        }
    }
}
