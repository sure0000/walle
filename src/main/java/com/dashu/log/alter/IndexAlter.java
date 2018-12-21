package com.dashu.log.alter;

import com.dashu.log.alterRules.ESIndexRule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description index告警
 * @Author: xuyouchang
 * @Date 2018/11/22 下午5:26
 **/
@Service
public class IndexAlter {
    @Resource
    private ESIndexRule esIndexRule;

    /**
     * 告警
     */
    public void alter(){
//        esIndexRule.isError();
        esIndexRule.isStopIndex();
    }
}
