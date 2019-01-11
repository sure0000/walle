package com.dashu.log.alterRules;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.monitor.net.MqDetection;

import java.net.SocketException;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/1/11 上午10:29
 **/
public class NetAlterRule {
    private String IP;
    private Integer PORT;

    public NetAlterRule(String ip ,Integer port){
        this.IP = ip;
        this.PORT = port;
    }

    public void isConnect(){
        MqDetection mqDetection = new MqDetection();
        WalleNotify notify = new WalleNotify();
        try {
           Boolean flag = mqDetection.isConnect(this.IP,this.PORT);
           if (flag == false){
               notify.sendMessage("net","mq is connected fail","mq");
           }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
