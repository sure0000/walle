package com.dashu.log.client.controller;

import com.dashu.log.client.service.ConfigService;
import com.dashu.log.dao.MessageAlterConfRepository;
import com.dashu.log.dao.MetricConfRepository;
import com.dashu.log.entity.MessageAlterConf;
import com.dashu.log.entity.MetricConf;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/19 上午10:06
 **/
@RestController
public class ConfigController {
    @Resource
    private MessageAlterConfRepository messageAlterConfRepository;
    @Resource
    private ConfigService configService;
    @Resource
    private MetricConfRepository metricConfRepository;


    /** 获取所有message conf **/
    @RequestMapping(value = "/config/getAllMessageConf",method = RequestMethod.GET)
    public List<MessageAlterConf> getAllMessageConf(){
        return messageAlterConfRepository.getAllConf();
    }


    /** 添加消息告警配置 **/
    @RequestMapping(value = "/config/addSingleMessageConf",method = RequestMethod.GET)
    public boolean addSingleMessageConf(@RequestParam(value = "messageAlterName")String messageAlterName,
                                        @RequestParam(value = "alterWay")String AlterWay,
                                        @RequestParam(value = "chargeMan")String chargeMan,
                                        @RequestParam(value = "isShow")Integer isShow,
                                        @RequestParam(value = "alterLevel")String alterLevel,
                                        @RequestParam(value = "frequency")Integer frequency){
        return configService.addSingleMessageConf(messageAlterName, AlterWay, chargeMan, isShow, alterLevel, frequency);
    }


    /** 删除单条消息告警配置 **/
    @RequestMapping(value = "/config/deleteSingleMessageConf",method = RequestMethod.GET)
    public boolean deleteSingleMessageConf(@RequestParam(value = "id")Integer id){
        return configService.deleteSingleMessageConf(id);
    }


    /** 获取所有metric conf **/
    @RequestMapping(value = "/config/getAllMetricConf",method = RequestMethod.GET)
    public List<MetricConf> getAllMetricConf(){
        return metricConfRepository.getAllMetricConf();
    }


    /** 添加metric消息告警 **/
    @RequestMapping(value = "/config/addSingleMetricConf",method = RequestMethod.GET)
    public boolean addSingleMetricConf(@RequestParam(value = "metricName")String metricName,
                                       @RequestParam(value = "alterLevel")String alterLevel,
                                       @RequestParam(value = "alterWay")String alterWay,
                                       @RequestParam(value = "chargeMan")String chargeMan,
                                       @RequestParam(value = "frequency")Integer frequency,
                                       @RequestParam(value = "isShow")Integer isShow,
                                       @RequestParam(value = "metricThreshold")Integer metricThreshold){
        return configService.addSingleMetricConf(metricName,alterLevel,alterWay,chargeMan,frequency,isShow,metricThreshold);
    }


    /** 删除单条metric告警配置 **/
    @RequestMapping(value = "/config/deleteSingleMetricConf",method = RequestMethod.GET)
    public boolean deleteSingleMetricConf(@RequestParam(value = "id")Integer id){
        return configService.deleteSingleMetricConf(id);
    }

}
