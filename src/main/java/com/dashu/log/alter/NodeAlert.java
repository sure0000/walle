package com.dashu.log.alter;

import com.dashu.log.alertRules.ESNodeRule;
import com.dashu.log.monitor.node.Node;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/21 下午6:15
 **/
public class NodeAlert {
    private String BASE_URL;
    private int OS_MEM_THRESHOLD;
    private int JVM_HEAP_THRESHOLD;
    private int FS_THRESHOLD;
    private static final String ALTER_NAME = "ES Node";

    public NodeAlert(String baseUrl,
                     int osMemThreshold,
                     int jvmHeapThreshold,
                     int fsThreshold) {
        this.BASE_URL = baseUrl;
        this.OS_MEM_THRESHOLD = osMemThreshold;
        this.JVM_HEAP_THRESHOLD = jvmHeapThreshold;
        this.FS_THRESHOLD = fsThreshold;
    }

    /**告警**/
    public void alert() {
        Node node = new Node(this.BASE_URL);
        Map<String,String> nodeAlertMap = new HashMap<>();
        for (JSONObject nodeInfoObject: node.getNodesInfo()) {
            String nodeName = nodeInfoObject.getString("name");
            ESNodeRule esNodeRule = new ESNodeRule(this.BASE_URL,nodeInfoObject);
            JSONObject checkFsObject = esNodeRule.checkFs(this.FS_THRESHOLD);
            JSONObject checkIndexObject = esNodeRule.checkIndex();
            JSONObject checkJvmHeapObject = esNodeRule.checkJVMHeap(this.JVM_HEAP_THRESHOLD);
            JSONObject checkOsMemObject = esNodeRule.checkOsMem(this.OS_MEM_THRESHOLD);

            if (checkFsObject.getInt("isAlert") == 1) {
                String fsInfo = "[" + nodeName + "] fs is used " + checkFsObject.getString("fsUsedPercent") + "%";
                nodeAlertMap.put("fsInfo",fsInfo);
            }
            if (checkIndexObject.getInt("isAlert") == 1) {
                String indexInfo = "[" + nodeName + "] index failed " + checkIndexObject.getInt("indexReject");
                nodeAlertMap.put("indexInfo", indexInfo);
            }
            if (checkJvmHeapObject.getInt("isAlert") == 1) {
                String jvmHeapInfo = "[" + nodeName + "] jvm heap used " + checkJvmHeapObject.getInt("jvmHeapUsedPercent") + "%";
                nodeAlertMap.put("jvmHeapInfo", jvmHeapInfo);
            }
            if (checkOsMemObject.getInt("isAlert") == 1) {
                String osMemInfo = "[" + nodeName + "] os mem used " + checkOsMemObject.getInt("osMemUsedPercent") + "%";
                nodeAlertMap.put("osMemInfo", osMemInfo);
            }
        }

        String alertInfo = "node check info is:\n" + nodeAlertMap.toString();
        WalleNotify notify = new WalleNotify();
        notify.sendMessage(ALTER_NAME, alertInfo, "node check");
    }
}
