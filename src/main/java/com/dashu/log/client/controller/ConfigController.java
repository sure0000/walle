package com.dashu.log.client.controller;

import com.dashu.log.client.ClientUtil;
import com.dashu.log.client.service.ConfigService;
import com.dashu.log.dao.MessageAlterConfRepository;
import com.dashu.log.dao.MetricConfRepository;
import com.dashu.log.entity.MessageAlterConf;
import com.dashu.log.entity.MetricConf;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/19 上午10:06
 **/
@RestController
@CrossOrigin
public class ConfigController {
    @Resource
    private MessageAlterConfRepository messageAlterConfRepository;
    @Resource
    private ConfigService configService;
    @Resource
    private MetricConfRepository metricConfRepository;
    @Resource
    private ClientUtil clientUtil;


    /** 获取所有message conf **/
    @RequestMapping(value = "/config/getAllMessageConf",method = RequestMethod.GET)
    public String getAllMessageConf(){
        try {
            List<MessageAlterConf> list = messageAlterConfRepository.getAllConf();
            return clientUtil.responseMessage(200,"",list);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }

    }


    /** 添加消息告警配置 **/
    @RequestMapping(value = "/config/addSingleMessageConf",method = RequestMethod.GET)
    public String addSingleMessageConf(@RequestParam(value = "messageAlterName")String messageAlterName,
                                        @RequestParam(value = "alterWay")String AlterWay,
                                        @RequestParam(value = "chargeMan")String chargeMan,
                                        @RequestParam(value = "isShow")Integer isShow,
                                        @RequestParam(value = "alterLevel")String alterLevel,
                                        @RequestParam(value = "frequency")Integer frequency){
        try {
            configService.addSingleMessageConf(messageAlterName, AlterWay, chargeMan, isShow, alterLevel, frequency);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }


    /** 删除单条消息告警配置 **/
    @RequestMapping(value = "/config/deleteSingleMessageConf",method = RequestMethod.GET)
    public String deleteSingleMessageConf(@RequestParam(value = "id")Integer id){
        try {
            configService.deleteSingleMessageConf(id);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }


    /** 获取所有metric conf **/
    @RequestMapping(value = "/config/getAllMetricConf",method = RequestMethod.GET)
    public String getAllMetricConf(){
        try {
            List<MetricConf> list = metricConfRepository.getAllMetricConf();
            return clientUtil.responseMessage(200,"",list);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }


    /** 添加metric消息告警 **/
    @RequestMapping(value = "/config/addSingleMetricConf",method = RequestMethod.GET)
    public String addSingleMetricConf(@RequestParam(value = "metricName")String metricName,
                                       @RequestParam(value = "alterLevel")String alterLevel,
                                       @RequestParam(value = "alterWay")String alterWay,
                                       @RequestParam(value = "chargeMan")String chargeMan,
                                       @RequestParam(value = "frequency")Integer frequency,
                                       @RequestParam(value = "isShow")Integer isShow,
                                       @RequestParam(value = "metricThreshold")Integer metricThreshold){
        try {
            configService.addSingleMetricConf(metricName,alterLevel,alterWay,chargeMan,frequency,isShow,metricThreshold);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }


    /** 删除单条metric告警配置 **/
    @RequestMapping(value = "/config/deleteSingleMetricConf",method = RequestMethod.GET)
    public String deleteSingleMetricConf(@RequestParam(value = "id")Integer id){
        try {
            configService.deleteSingleMetricConf(id);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }

}
