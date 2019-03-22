package com.dashu.log.alter;

import com.dashu.log.alertRules.MetricRule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description metric alter
 * @Author: xuyouchang
 * @Date 2018/12/12 下午1:41
 **/
@RestController
public class MetricAlter {
    @Resource
    private MetricRule metricRule;

    @RequestMapping(value = "/metric/",method = RequestMethod.GET)
    public void isAlter(@RequestParam(value = "metricname")String metricname,
                        @RequestParam(value = "value")int value){
        metricRule.isExceed(metricname,value);
    }

}
