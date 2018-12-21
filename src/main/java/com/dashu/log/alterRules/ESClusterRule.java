package com.dashu.log.alterRules;

import com.dashu.log.Walle;
import com.dashu.log.alter.WalleNotify;
import com.dashu.log.monitor.cluster.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/11/22 下午5:09
 **/
public class ESClusterRule {
    private static final Logger logger = LoggerFactory.getLogger(ESIndexRule.class);

    /**
     * es是否存在拒绝
     */
    public void isReject(){
        Cluster cluster = new Cluster();
        String ret = cluster.reject();
        if (ret.equals("no reject")){
            logger.info("there is no reject!");
        }else {
            WalleNotify notify = new WalleNotify();
            notify.sendMessage("es reject",ret,"es reject");
            logger.warn(ret);
        }
    }

    /**
     * es集群是否健康
     */
    public void isHealthRule(){
        Cluster cluster = new Cluster();
        boolean ishealth = cluster.isHealth();
        if (!ishealth){
            WalleNotify notify =new WalleNotify();
            notify.sendMessage("es cluster","es cluster is not health now, please check it!","es cluster");
            logger.warn("es cluster is not health now, please check it!");
        }else {
            logger.info("es cluster is health!");
        }

    }
}
