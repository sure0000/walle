package com.dashu.log.client.controller;

import com.dashu.log.client.ClientUtil;
import com.dashu.log.client.service.ConfigService;
import com.dashu.log.dao.MessageAlterConfRepository;
import com.dashu.log.dao.MetricConfRepository;
import com.dashu.log.entity.MessageAlterConf;
import com.dashu.log.entity.MetricConf;
import org.json.JSONObject;
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
    public String getAllMessageConf(@RequestParam(value = "page")Integer page){
        Integer pageSize = 10;
        try {
            List<MessageAlterConf> list = messageAlterConfRepository.getAllConf();
            int total = list.size();
            if (list.size() >= page*pageSize){
                list = list.subList((page-1)*pageSize,page*pageSize);
            }else if (list.size() != 0){
                list = list.subList((page-1)*pageSize,list.size());
            }
            JSONObject result = new JSONObject();
            result.put("total",total);
            result.put("list",list);
            result.put("currentPage",page);
            return clientUtil.responseMessage(200,"",result);
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
    public String getAllMetricConf(@RequestParam(value = "page")Integer page){
        Integer pageSize = 10;
        try {
            List<MetricConf> list = metricConfRepository.getAllMetricConf();
            int total = list.size();
            if (list.size() >= page*pageSize){
                list = list.subList((page-1)*pageSize,page*pageSize);
            }else if (list.size() != 0){
                list = list.subList((page-1)*pageSize,list.size());
            }
            JSONObject result = new JSONObject();
            result.put("total",total);
            result.put("list",list);
            result.put("currentPage",page);
            return clientUtil.responseMessage(200,"",result);
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
