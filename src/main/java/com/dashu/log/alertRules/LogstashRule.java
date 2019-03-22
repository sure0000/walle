package com.dashu.log.alertRules;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.monitor.logstash.LogstashDetect;
import com.dashu.log.util.RunCmdLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/11/26 下午4:59
 **/
public class LogstashRule {
    private static final Logger logger = LoggerFactory.getLogger(LogstashRule.class);

    /**
     * logstash是否存活
     * @param cmd
     * @param hostname
     */
    public void isAliveRule(String cmd,String hostname){
        LogstashDetect logstashDetect = new LogstashDetect(hostname);
        boolean isAlive = logstashDetect.isAlive();
        if (!isAlive){
            logger.warn("the logstash of "+hostname+" is not alive !");
            WalleNotify notify = new WalleNotify();
            notify.sendMessage("logstash","the logstash of "+hostname+" is not alive !","logstash down");
            RunCmdLine runCmdLine = new RunCmdLine(this);
            List ret = runCmdLine.callShell(cmd);
            if (ret == null){
                logger.warn("the logstash of "+hostname+" is restart fail,please check it !");
                notify.sendMessage("logstash","the logstash of "+hostname+" is restart fail,please check it !","logstash start fail");
            }else{
                logger.info("the logstash of "+hostname+" is up !");
                notify.sendMessage("logstash","the logstash of "+hostname+" is up !","logstash start up");
            }
        }else{
            logger.info("the logstash of "+hostname+" is health !");
        }
    }
}
