package com.dashu.log.alterRules;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.dao.MessageAlterConfRepository;
import com.dashu.log.dao.SelfAlterHistoryRepository;
import com.dashu.log.entity.MessageAlterConf;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/13 上午10:37
 **/
@Component
public class MessageRule {
    @Resource
    private MessageAlterConfRepository messageAlterConfRepository;
    @Resource
    private SelfAlterHistoryRepository selfAlterHistoryRepository;

    public void sendMessage(String messageName,String messageContent,String service){
        MessageAlterConf messageAlterConf = messageAlterConfRepository.getSingleConf(messageName);
        selfAlterHistoryRepository.addSingleAlter(messageName,messageAlterConf.getAlterWay(),messageAlterConf.getChargeMan(),messageAlterConf.getIsShow(),messageContent,new Date(),"message");
        WalleNotify notify = new WalleNotify();
        notify.sendMessage(messageName,messageContent,service);
    }
}
