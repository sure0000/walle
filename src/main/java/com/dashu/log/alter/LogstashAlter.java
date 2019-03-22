package com.dashu.log.alter;

import com.dashu.log.alertRules.LogstashRule;

/**
 * @Description logstash告警
 * @Author: xuyouchang
 * @Date 2018/11/26 下午5:12
 **/
public class LogstashAlter {
    private static final String SSH_BASH_CMD = null;
    private static String HOSTNAME = null;
    private static String SSH_BASE_CMD = null;
    private static String START_LOGSTASH = "nohup /root/logstash-6.3.2/bin/logstash --config.reload.automatic >/data0/log/logstash/logstash/log 2>&1 >&";
    private static String START_LOGSTASH_CMD = null;

    public LogstashAlter(String hostname){
        this.HOSTNAME = hostname;
        this.SSH_BASE_CMD = "ssh root@"+this.HOSTNAME+" ";
        this.START_LOGSTASH_CMD = this.SSH_BASE_CMD + this.START_LOGSTASH;
    }


    /**
     * 告警
     */
    public void alter(){
        LogstashRule logstashRule = new LogstashRule();
        logstashRule.isAliveRule(this.START_LOGSTASH_CMD,this.HOSTNAME);
    }
}
