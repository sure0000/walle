package com.dashu.log.monitor.node;

import com.dashu.log.monitor.cluster.Cluster;
import com.dashu.log.util.HttpUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 获取 es 节点相关信息
 * @Author: xuyouchang
 * @Date 2019/3/20 下午2:19
 **/
public class Node {
    private String BASE_URL;
    private static final String NODES_STATS = "_nodes/stats";
    private static final Logger logger = LoggerFactory.getLogger(Cluster.class);

    public Node (String baseUrl){
        this.BASE_URL = baseUrl;
    }

    /** 读取节点中 http 状态信息 **/
    public JSONObject getNodeHttp(JSONObject nodeJsonobject) {
        JSONObject httpObject = nodeJsonobject.getJSONObject("http");
        return httpObject;
    }

    /** 获取节点中 fs 状态信息 **/
    public JSONObject getNodeFs(JSONObject nodeJsonobject) {
        JSONObject fsObject = nodeJsonobject.getJSONObject("fs");
        return fsObject;
    }

    /** 获取节点中 thread_pool 状态信息 **/
    public JSONObject getNodeThreadPool(JSONObject nodeJsonobject) {
        JSONObject threadPoolObject = nodeJsonobject.getJSONObject("thread_pool");
        return threadPoolObject;
    }

    /** 获取节点中 jvm 状态信息 **/
    public JSONObject getNodeJvm(JSONObject nodeJsonobject) {
        JSONObject jvmObject = nodeJsonobject.getJSONObject("jvm");
        return jvmObject;
    }

    /** 获取节点中 os 状态信息 **/
    public JSONObject getNodeOs(JSONObject nodeJsonobject) {
        JSONObject osObject = nodeJsonobject.getJSONObject("os");
        return osObject;
    }

    /** 获取节点中 indices 状态信息 **/
    public JSONObject getNodeIndices(JSONObject nodeJsonObject) {
        JSONObject indicesObject = nodeJsonObject.getJSONObject("indices");
        return indicesObject;
    }

    /** 获取节点状态信息 */
    public List<JSONObject> getNodesInfo() {
        HttpUtil httpUtil = new HttpUtil(Cluster.class);
        String url = this.BASE_URL + NODES_STATS;
        List<JSONObject> listNodeInfo = new ArrayList<>();
        try {
            String result = httpUtil.get(url);
            JSONObject resultObject = new JSONObject(result);
            JSONObject nodeObject = resultObject.getJSONObject("nodes");
            Iterator<String> nodesKey = nodeObject.keys();
            while (nodesKey.hasNext()){
                String key = nodesKey.next();
                String value = nodeObject.get(key).toString();
                JSONObject statObject = new JSONObject(value);
                listNodeInfo.add(statObject);
            }
            return listNodeInfo;
        } catch (IOException e) {
            logger.error("node stat request fail: "+e.getMessage());
            return null;
        }
    }
}
