package com.dashu.log;

import com.dashu.log.alter.ESClusterAlter;
import com.dashu.log.alter.IndexAlter;
import com.dashu.log.alter.NetAlter;
import com.dashu.log.alter.multiThread.LogstashThread;
import com.dashu.log.client.dao.LogstashConfRepository;
import com.dashu.log.entity.FilebeatConf;
import com.dashu.log.monitor.dao.FileBeatConfRepository;
import com.dashu.log.alter.multiThread.FilebeatThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 监控、告警任务调度
 * @Author: xuyouchang
 * @Date 2018/11/23 下午1:22
 **/
@Component
public class Walle {
    private static final Logger logger = LoggerFactory.getLogger(Walle.class);
    @Resource
    private IndexAlter indexAlter;
    @Resource
    private FileBeatConfRepository fileBeatConfRepository;
    @Resource
    private LogstashConfRepository logstashConfRepository;

    /** logstash **/
    @Scheduled(cron = "0 */5 * * * *")
    public void logstashAlter(){
        List<String> hostanmelist = logstashConfRepository.getAllHostanme();
        for (String hostname : hostanmelist){
            LogstashThread logstashThread = new LogstashThread(hostname);
            logstashThread.start();
        }
    }

    /** es index */
    @Scheduled(cron = "0 */2 * * * *")
    public void indexAlter(){
        indexAlter.alter();
    }

    /** filebeat */
    @Scheduled(cron = "0 */5 * * * *")
    public void filebeatAlter(){
        List<FilebeatConf> filebeatConfList = fileBeatConfRepository.getAllHostname();
        for (FilebeatConf filebeatConf : filebeatConfList){
            FilebeatThread filebeatThread = new FilebeatThread(filebeatConf.getHostname());
            filebeatThread.start();
        }
    }

    /** es cluster */
    @Scheduled(cron = "0 */5 * * * *")
    public void esClusterAlter(){
        ESClusterAlter esClusterAlter = new ESClusterAlter();
        esClusterAlter.alter();
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void netAlter(){
        NetAlter netAlter = new NetAlter();
        netAlter.isAlter();
    }



}
