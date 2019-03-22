package com.dashu.log.alter;

import com.dashu.log.alertRules.NetAlterRule;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/1/11 上午10:24
 **/
public class NetAlter {

    public void isAlter(){
        NetAlterRule netAlterRule = new NetAlterRule("172.17.15.13",9876);
        netAlterRule.isConnect();
    }


}
