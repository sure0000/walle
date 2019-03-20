package com.dashu.log.alterRules;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.monitor.cluster.Cluster;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description es 集群告警规则
 * @Author xuyouchang
 * @Date 2018/11/22 下午5:09
 **/
public class ESClusterRule {
    private static final Logger logger = LoggerFactory.getLogger(ESIndexRule.class);
    private static final String ALTER_NAME = "ES";
    private static final String SERVICE_NAME = "cluster";
//    private String BASE_URL;
//
//    public ESClusterRule(String baseUrl){
//        this.BASE_URL = baseUrl;
//    }

    /**
     * 检查集群系统JVM heap
     * @param clusterNodesObject
     * @param threshold JVM heap 使用阈值
     * @return
     */
    public JSONObject checkJvmHeap(JSONObject clusterNodesObject, Integer threshold) {
        if (threshold == null){
            threshold = 80;
        }

        Long heapUsed = clusterNodesObject.getJSONObject("jvm").getJSONObject("mem").getLong("heap_used_in_bytes");
        Long heapMax = clusterNodesObject.getJSONObject("jvm").getJSONObject("mem").getLong("heap_max_in_bytes");
        Long heapUsedPercent = (heapUsed*100/heapMax);
        int isAlert = 0;

        if (heapUsedPercent >= threshold){
            isAlert = 1;
        }

        JSONObject checkResult = new JSONObject();
        checkResult.put("isAlert", isAlert);
        checkResult.put("heapUsedPercent", heapUsedPercent);
        checkResult.put("desc", "jvm heap 使用率");
        return checkResult;
    }

    /**
     * 检查集群系统内存
     * @param clusterNodesObject 集群节点状态信息
     * @param threshold 集群系统内存使用阈值
     * @return
     */
    public JSONObject checkOsMem(JSONObject clusterNodesObject, Integer threshold) {
        if (threshold == null){
            threshold = 80;
        }

        int memUsedPercent = clusterNodesObject.getJSONObject("os").getJSONObject("mem").getInt("used_percent");
        int isAlert = 0;

        if (memUsedPercent >= threshold) {
             isAlert = 1;
        }

        JSONObject checkResult = new JSONObject();
        checkResult.put("isAlert", isAlert);
        checkResult.put("memUsedPercent", memUsedPercent);
        checkResult.put("desc", "集群内存使用率");
        return checkResult;
    }

    /**es集群是否健康*/
    public boolean isClusterHealth(String clusterStatus) {
        if ("green".equalsIgnoreCase(clusterStatus)) {
            return true;
        }else {
            return false;
        }
    }

//    /** 是否存在节点索引插入失败 **/
//    public void indexFailedRule(){
//        Cluster cluster = new Cluster(this.BASE_URL);
//        String indexFailedInfo = cluster.indexFailed();
//        if (indexFailedInfo != null){
//            WalleNotify notify = new WalleNotify();
//            notify.sendMessage(ALTER_NAME,indexFailedInfo,SERVICE_NAME);
//            logger.warn("索引插入失败："+indexFailedInfo);
//        }
//    }

//    /**es是否存在拒绝插入操作*/
//    public void indexRejectRule(){
//        Cluster cluster = new Cluster(this.BASE_URL);
//        String rejectInfo = cluster.reject();
//        if (rejectInfo != null){
//            WalleNotify notify = new WalleNotify();
//            notify.sendMessage(ALTER_NAME,rejectInfo,SERVICE_NAME);
//            logger.warn("索引插入拒绝："+rejectInfo);
//        }
//    }


//    public void clusterHealthRule(){
//        Cluster cluster = new Cluster(this.BASE_URL);
//        boolean ishealth = cluster.health();
//        if (!ishealth){
//            WalleNotify notify =new WalleNotify();
//            String info = "es集群状态不健康！";
//            notify.sendMessage(ALTER_NAME,info,SERVICE_NAME);
//            logger.warn(info);
//        }
//        logger.info("es cluster is health!");
//
//    }
}
