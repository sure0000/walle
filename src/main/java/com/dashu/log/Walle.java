package com.dashu.log;

import com.dashu.log.alter.ESClusterAlert;
import com.dashu.log.alter.IndexAlert;
import com.dashu.log.alter.NetAlter;
import com.dashu.log.alter.NodeAlert;
import com.dashu.log.alter.multiThread.LogstashThread;
import com.dashu.log.client.dao.LogstashConfRepository;
import com.dashu.log.entity.FilebeatConf;
import com.dashu.log.monitor.dao.FileBeatConfRepository;
import com.dashu.log.alter.multiThread.FilebeatThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 监控、告警任务调度
 * @Author xuyouchang
 * @Date 2018/11/23 下午1:22
 **/
@Component
public class Walle {
    private static final Logger logger = LoggerFactory.getLogger(Walle.class);
    @Autowired
    private Environment env;


    @Scheduled(cron = "* * * * * *")
    public void nodeAlert() {
        String baseUrl = getBaseUrl(env);
        int osMemThreshold = Integer.parseInt(env.getProperty("es.node.osMem.threshold"));
        int jvmThreshold = Integer.parseInt(env.getProperty("es.node.jvmHeap.threshold"));
        int fsThreshold = Integer.parseInt(env.getProperty("es.node.fs.threshold"));
        NodeAlert nodeAlert = new NodeAlert(baseUrl,osMemThreshold,jvmThreshold,fsThreshold);
        nodeAlert.alert();
    }

//    /** es index */
//    @Scheduled(cron = "* * * * * *")
//    public void indexAlter(){
//       String baseUrl = getBaseUrl(env);
//       IndexAlert indexAlert = new IndexAlert(baseUrl);
//       indexAlert.alert();
//    }


    /** es cluster */
    @Scheduled(cron = "* * * * * *")
    public void esClusterAlter(){
        String baseUrl = getBaseUrl(env);
        int osMemThreshold = Integer.parseInt(env.getProperty("es.cluster.osMem.threshold"));
        int jvmThreshold = Integer.parseInt(env.getProperty("es.cluster.jvmHeap.threshold"));
        int fsThreshold = Integer.parseInt(env.getProperty("es.cluster.fs.threshold"));
        ESClusterAlert esClusterAlert = new ESClusterAlert(baseUrl,osMemThreshold,jvmThreshold,fsThreshold);
        esClusterAlert.alert();
    }



    /** 构造es基础请求URL **/
    public String getBaseUrl(Environment env) {
        String host = env.getProperty("es.host");
        String port = env.getProperty("es.port");
        String username = env.getProperty("es.username");
        String password = env.getProperty("es.password");

        String baseUrl = "http://" + username + ":" + password +
                "@" + host + ":" + port + "/";
        return baseUrl;
    }


    //    /** filebeat */
//    @Scheduled(cron = "0 */5 * * * *")
//    public void filebeatAlter(){
//        List<FilebeatConf> filebeatConfList = fileBeatConfRepository.getAllHostname();
//        for (FilebeatConf filebeatConf : filebeatConfList){
//            FilebeatThread filebeatThread = new FilebeatThread(filebeatConf.getHostname());
//            filebeatThread.start();
//        }
//    }

//    @Scheduled(cron = "0 */10 * * * *")
//    public void netAlter(){
//        NetAlter netAlter = new NetAlter();
//        netAlter.isAlter();
//    }

    //    /** logstash **/
//    @Scheduled(cron = "0 */5 * * * *")
//    public void logstashAlter(){
//        logger.info("start logstash detection");
//        List<String> listLogstah = logstashConfRepository.getAllHostanme();
//        for (String hostname : listLogstah){
//            LogstashThread logstashThread = new LogstashThread(hostname);
//            logstashThread.start();
//        }
//    }



}
