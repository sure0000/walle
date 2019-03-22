package com.dashu.log.alertRules;

import com.dashu.log.monitor.node.Node;
import org.json.JSONObject;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/21 下午3:40
 **/
public class ESNodeRule {
    private String BASE_URL;
    private JSONObject nodeInfoObject;


    public ESNodeRule(String baseUrl, JSONObject nodeInfoObject) {
        this.BASE_URL = baseUrl;
        this.nodeInfoObject = nodeInfoObject;
    }

    /**
     *  node 磁盘空间检查
     * @param threshold 阈值
     * @return
     */
    public JSONObject checkFs(int threshold) {
        Node node = new Node(this.BASE_URL);
        JSONObject fsObject = node.getNodeFs(this.nodeInfoObject);
        Long totalBytes = fsObject.getJSONObject("total").getLong("total_in_bytes");
        Long availableBytes = fsObject.getJSONObject("total").getLong("available_in_bytes");
        Long usedBytes = totalBytes - availableBytes;
        Long fsUsedPercent = usedBytes*100/totalBytes;
        int isAlert = 0;

        if (fsUsedPercent > threshold) {
            isAlert = 1;
        }

        JSONObject checkObject = new JSONObject();
        checkObject.put("isAlert", isAlert);
        checkObject.put("fsUsedPercent", fsUsedPercent);
        checkObject.put("desc", "node fs used Percent check");
        return checkObject;

    }


    /** node index reject 检查 **/
    public JSONObject checkIndex() {
        Node node = new Node(this.BASE_URL);
        JSONObject threadPoolObject = node.getNodeThreadPool(this.nodeInfoObject);
        int indexReject = threadPoolObject.getJSONObject("index").getInt("rejected");
        int isAlert = 0;

        if (indexReject >0) {
            isAlert = 1;
        }

        JSONObject checkObject = new JSONObject();
        checkObject.put("isAlert", isAlert);
        checkObject.put("indexReject", indexReject);
        checkObject.put("desc", "node index reject check");
        return checkObject;
    }

    /** node jvm heap检查 **/
    public JSONObject checkJVMHeap(int threshold) {
        Node node = new Node(this.BASE_URL);
        JSONObject jvmObject = node.getNodeJvm(this.nodeInfoObject);
        int jvmHeapUsedPercent = jvmObject.getJSONObject("mem").getInt("heap_used_percent");
        int isAlert = 0;

        if (jvmHeapUsedPercent > threshold) {
            isAlert = 1;
        }

        JSONObject checkObject = new JSONObject();
        checkObject.put("isAlert", isAlert);
        checkObject.put("jvmHeapUsedPercent", jvmHeapUsedPercent);
        checkObject.put("desc", "node jvm heap check");
        return checkObject;
    }

    /**
     * node os 内存检查
     * @param threshold
     * @return
     */
    public JSONObject checkOsMem(int threshold) {
        Node node = new Node(this.BASE_URL);
        JSONObject oSObject = node.getNodeOs(this.nodeInfoObject);
        Long osMemUsedPercent = oSObject.getJSONObject("mem").getLong("used_percent");
        int isAlert = 0;

        if (osMemUsedPercent > threshold) {
            isAlert = 1;
        }

        JSONObject checkObject = new JSONObject();
        checkObject.put("isAlert", isAlert);
        checkObject.put("osMemUsedPercent", osMemUsedPercent);
        checkObject.put("desc", "node os mem check");
        return checkObject;
    }
}
