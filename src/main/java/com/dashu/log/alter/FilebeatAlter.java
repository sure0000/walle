package com.dashu.log.alter;

import com.dashu.log.alterRules.FilebeatRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description filebeat告警
 * @Author: xuyouchang
 * @Date 2018/11/22 下午2:42
 **/
public class FilebeatAlter {
    private static final Logger logger = LoggerFactory.getLogger(FilebeatAlter.class);
    private static String HOSTNAME = null;
    private static String SSH_BASE_CMD = null;
    private static final String START_FILEBEAT = "service filebeat start";
    private static  String  START_FILEBEAT_CMD = null;

    public FilebeatAlter(String hostname){
        this.HOSTNAME = hostname;
        this.SSH_BASE_CMD = "ssh root@"+this.HOSTNAME+" ";
        this.START_FILEBEAT_CMD = this.SSH_BASE_CMD + this.START_FILEBEAT;
    }

    /**
     * 告警
     */
    public void alter(){
        FilebeatRule filebeatRule = new FilebeatRule();
        filebeatRule.isAliveRule(this.START_FILEBEAT_CMD,this.HOSTNAME);
    }

}
