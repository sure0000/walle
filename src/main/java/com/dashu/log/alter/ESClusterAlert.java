package com.dashu.log.alter;

import com.dashu.log.alertRules.ESClusterRule;
import com.dashu.log.monitor.cluster.Cluster;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description es集群告警
 * @Author xuyouchang
 * @Date 2018/11/22 下午5:23
 **/
public class ESClusterAlert {
    private String BASE_URL;
    private int OS_MEM_THRESHOLD;
    private int JVM_HEAP_THRESHOLD;
    private int FS_THRESHOLD;
    private static final String ALTER_NAME = "ES Cluster";

    public ESClusterAlert(String baseUrl,
                          int osMemThreshold,
                          int jvmMemThrehold,
                          int fsThrehold){
        this.BASE_URL = baseUrl;
        this.OS_MEM_THRESHOLD = osMemThreshold;
        this.JVM_HEAP_THRESHOLD = jvmMemThrehold;
        this.FS_THRESHOLD = fsThrehold;
    }

    /**
     * 告警
     */
    public void alert(){
        Cluster cluster = new Cluster(this.BASE_URL);
        JSONObject clusterStatInfo = cluster.getClusterInfo();
        JSONObject clusterNodes = cluster.getClusterNodes(clusterStatInfo);

        ESClusterRule esClusterRule = new ESClusterRule();
        boolean isHealth = esClusterRule.isClusterHealth(clusterStatInfo.getString("status"));
        JSONObject checkOsMem = esClusterRule.checkOsMem(clusterNodes, this.OS_MEM_THRESHOLD);
        JSONObject checkJvmHeap = esClusterRule.checkJvmHeap(clusterNodes, this.JVM_HEAP_THRESHOLD);
        JSONObject checkFs = esClusterRule.checkFs(clusterNodes, this.FS_THRESHOLD);
        Map<String, String> clusterAlertMap = new HashMap<>();

        if (!isHealth) {
            String healthInfo = "es cluster is not health!";
            clusterAlertMap.put("healthInfo",healthInfo);
        }

        if (checkOsMem.getInt("isAlert") == 1) {
            String osMemInfo = "os mem used " + checkOsMem.getInt("memUsedPercent") + "%";
            clusterAlertMap.put("osMemInfo", osMemInfo);
        }

        if (checkJvmHeap.getInt("isAlert") == 1) {
            String jvmHeapInfo = "jvm heap used " + checkJvmHeap.getInt("heapUsedPercent") + "%";
            clusterAlertMap.put("jvmHeapInfo", jvmHeapInfo);
        }

        if (checkFs.getInt("isAlert") == 1) {
            String fsInfo = "fs used " + checkFs.getInt("fsUsedPercent") + "%";
            clusterAlertMap.put("fsInfo", fsInfo);
        }

        if (clusterAlertMap.size() > 0) {
            String alertInfo = "the cluster check info is:\n" + clusterAlertMap.toString();
            WalleNotify notify = new WalleNotify();
            notify.sendMessage(ALTER_NAME, alertInfo, "cluster check");
        }

    }
}
