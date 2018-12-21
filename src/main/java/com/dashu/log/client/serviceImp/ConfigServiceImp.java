package com.dashu.log.client.serviceImp;

import com.dashu.log.client.service.ConfigService;
import com.dashu.log.dao.MessageAlterConfRepository;
import com.dashu.log.dao.MetricConfRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/19 上午10:43
 **/
@Service
public class ConfigServiceImp implements ConfigService {
    @Resource
    private MessageAlterConfRepository messageAlterConfRepository;
    @Resource
    private MetricConfRepository metricConfRepository;

    @Override
    public boolean addSingleMessageConf(String messageAlterName, String AlterWay, String chargeMan, Integer isShow, String alterLevel, Integer frequency) {
        if (messageAlterName == "" || AlterWay == "" || chargeMan == "" || alterLevel == ""){
            return false;
        }
        if (isShow == null || frequency== null){
            return false;
        }
        try {
            messageAlterConfRepository.addSingleConf(messageAlterName, AlterWay, chargeMan, isShow, alterLevel, frequency);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSingleMessageConf(Integer id) {
        if (id == null){
            return false;
        }
        try {
            messageAlterConfRepository.deleteSingleConfById(id);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean addSingleMetricConf(String metricName, String alterLevel, String alterWay, String chargeMan, Integer frequency, Integer isShow, Integer metricThreshold) {
        if (metricName == "" || alterLevel == "" || alterWay == "" || chargeMan == ""){
            return false;
        }
        if (frequency == null || isShow == null || metricThreshold == null){
            return false;
        }
        try {
            metricConfRepository.addSingleConf(metricName,alterLevel,alterWay,chargeMan,frequency,isShow,metricThreshold);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean deleteSingleMetricConf(Integer id) {
        if (id == null){
            return false;
        }
        try {
            metricConfRepository.deleteSingleConfById(id);
            return true;
        } catch (Exception e){
            return false;

        }
    }
}
