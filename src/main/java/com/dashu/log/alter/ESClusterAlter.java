package com.dashu.log.alter;

import com.dashu.log.alterRules.ESClusterRule;
import com.dashu.log.monitor.cluster.Cluster;
import org.json.JSONObject;

/**
 * @Description es集群告警
 * @Author xuyouchang
 * @Date 2018/11/22 下午5:23
 **/
public class ESClusterAlter {
    private String BASE_URL;
    private int OS_MEM_THRESHOLD;
    private int JVM_HEAP_THRESHOLD;
    private static final String ALTER_NAME = "ES Cluster";

    public ESClusterAlter(String baseUrl,
                          int osMemThreshold,
                          int jvmMemThrehold){
        this.BASE_URL = baseUrl;
        this.OS_MEM_THRESHOLD = osMemThreshold;
        this.JVM_HEAP_THRESHOLD = jvmMemThrehold;
    }

    /**
     * 告警
     */
    public void alter(){
        Cluster cluster = new Cluster(this.BASE_URL);
        JSONObject clusterStatInfo = cluster.getClusterInfo();
        JSONObject clusterNodes = cluster.getClusterNodes(clusterStatInfo);
        WalleNotify notify = new WalleNotify();

        ESClusterRule esClusterRule = new ESClusterRule();
        boolean isHealth = esClusterRule.isClusterHealth(clusterStatInfo.getString("status"));
        JSONObject checkOsMem = esClusterRule.checkOsMem(clusterNodes, this.OS_MEM_THRESHOLD);
        JSONObject checkJvmHeap = esClusterRule.checkJvmHeap(clusterNodes, this.JVM_HEAP_THRESHOLD);

        if (!isHealth) {
            notify.sendMessage(ALTER_NAME, "es cluster is not health!", "health");
        }

        if (checkOsMem.getInt("isAlter") == 1) {
            String info = "os mem used " + checkOsMem.getInt("memUsedPercent") + "%";
            notify.sendMessage(ALTER_NAME, info, "Mem");
        }

        if (checkJvmHeap.getInt("isAlert") == 1) {
            String info = "jvm heap used " + checkJvmHeap.get("heapUsedPercent") + "%";
            notify.sendMessage(ALTER_NAME, info, "Mem");
        }


    }
}
