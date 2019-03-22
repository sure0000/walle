package com.dashu.log.alter;

import com.dashu.log.alertRules.MessageRule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/13 上午9:51
 **/
@RestController
public class MessageAlter {
    @Resource
    private MessageRule messageRule;

    @RequestMapping(value = "/message")
    public void alter(@RequestParam(value = "messageName")String messageName,
                      @RequestParam(value = "messageContent")String messageContent,
                      @RequestParam(value = "service")String service){
    messageRule.sendMessage(messageName,messageContent,service);

    }
}
