package com.dashu.log.alterRules;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.monitor.filebeat.FilebeatDetection;
import com.dashu.log.util.RunCmdLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description filebeat告警规则
 * @Author: xuyouchang
 * @Date 2018/11/22 下午4:52
 **/
public class FilebeatRule {
    private static final Logger logger = LoggerFactory.getLogger(FilebeatRule.class);

    /**
     * filebeat是否存活
     * @param cmd
     * @param hostname
     */
    public void isAliveRule(String cmd,String hostname){
        FilebeatDetection filebeatDetection = new FilebeatDetection(hostname);
        boolean isAlive = filebeatDetection.isAlive();
        if (!isAlive){
            WalleNotify notify = new WalleNotify();
            notify.sendMessage("filebeat-down","the filebeat of "+hostname+" is down!","filebeat-down");
            logger.warn("the filebeat of "+hostname+" is down!");
            RunCmdLine runCmdLine = new RunCmdLine(this);
            List ret = runCmdLine.callShell(cmd);
            if (ret == null){
                notify.sendMessage("filebeat-start-fail","we try to up the filebeat of "+hostname+",but fail please check it!","filebeat-start-fail");
                logger.warn("we try to up the filebeat of "+hostname+",but fail please check it!");
            }else{
                notify.sendMessage("filebeat-start","the filebeat of "+hostname+" is up!","filebeat-start");
                logger.warn("the filebeat of "+hostname+" is up!");
            }
        }else{
            logger.info("the filebeat of "+hostname+" is health!");
        }
    }

}
