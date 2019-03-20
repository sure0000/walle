package com.dashu.log.monitor.cluster;

import com.dashu.log.util.HttpUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * @Description es集群级别服务指标监控
 * 检测项：es集群健康检查、索引插入失败数检测、索引插入拒绝数检测
 * @Author: xuyouchang
 * @Date 2018/11/1 上午10:39
 **/
public class Cluster {
    private String BASE_URL;
    private static final String NODES_STATS = "_nodes/stats";
    private static final String CLUSTER_HEALTH = "_cluster/health";
    private static final String CLUSTER_STATS = "_cluster/stats";
    private static final Logger logger = LoggerFactory.getLogger(Cluster.class);

    public Cluster (String baseUrl){
        this.BASE_URL = baseUrl;
    }

    /** 数据插入失败检测 */
    public String  indexFailed(){
        List<JSONObject> listNodeInfo = getNodesInfo(this.BASE_URL);
        List<String> retList = new ArrayList<>();
        if (listNodeInfo == null){
            return null;
        }
        for (JSONObject nodeInfo: listNodeInfo){
            String nodename = nodeInfo.get("name").toString();
            JSONObject indicesObject = new JSONObject(nodeInfo.get("indices").toString());
            JSONObject indexingObject = new JSONObject(indicesObject.get("indexing").toString());
            String indexFailedNum = indexingObject.get("index_failed").toString();
            if (!indexFailedNum.equals("0")){
                String indexFailMessage = nodename+" is index fail ,the number is "+ indexFailedNum;
                retList.add(indexFailMessage);
            }
        }
        if (retList.isEmpty()){
            return null;
        }
        return retList.toString();
    }

    /**节点拒绝数检测*/
    public String reject(){
        List<JSONObject> listNodeInfo = getNodesInfo(this.BASE_URL);
        List<String> retList = new ArrayList<>();
        if (listNodeInfo == null){
            return null;
        }
        for (JSONObject nodeInfo : listNodeInfo){
            String nodename = nodeInfo.get("name").toString();
            JSONObject threadPoolObject = new JSONObject(nodeInfo.get("thread_pool").toString());
            JSONObject indexObject = new JSONObject(threadPoolObject.get("index").toString());
            Integer indexRejected = Integer.parseInt(indexObject.get("rejected").toString());
            if (indexRejected != 0){
                String ret = nodename+":"+"index rejected("+indexRejected+")";
                retList.add(ret);
            }
        }
        if (retList.size() != 0){
            return retList.toString();
        }
        return null;

    }

    /**集群健康监控*/
    public  boolean health() {
        String URL = BASE_URL + CLUSTER_HEALTH;
        HttpUtil httpUtil = new HttpUtil(this);
        try {
            String clusterHealth = httpUtil.get(URL);
            JSONObject healthObject = new JSONObject(clusterHealth);
            String status = healthObject.get("status").toString();
            if (status.equals("green")){
                return true;
            }
            return false;
        } catch (IOException e) {
            logger.error(e.toString());
            return false;
        }

    }

    /** 获取节点信息 */
    public static List<JSONObject> getNodesInfo(String baseUrl){
        HttpUtil httpUtil = new HttpUtil(Cluster.class);
        String url = baseUrl+NODES_STATS;
        List<JSONObject> listNodeInfo = new ArrayList<>();
        try {
            String result = httpUtil.get(url);
            JSONObject resultObject = new JSONObject(result);
            JSONObject nodeObject = new JSONObject(resultObject.get("nodes").toString());
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

    /** 获取集群状态 **/
    public String getClusterStatus (JSONObject clusterInfo) {
        String status = clusterInfo.getString("status");
        return status;
    }

    /** 获取集群状态中 nodes 状态信息 **/
    public JSONObject getClusterNodes (JSONObject clusterInfo){
        JSONObject nodesObject = clusterInfo.getJSONObject("nodes");
        return nodesObject;
    }

    /** 获取集群状态中 indices 状态信息 **/
    public JSONObject getClusterIndices (JSONObject clusterInfo){
        JSONObject indicesObject = clusterInfo.getJSONObject("indices");
        return indicesObject;
    }

    /** 获取集群状态信息 **/
    public JSONObject getClusterInfo (){
        HttpUtil httpUtil = new HttpUtil(Cluster.class);
        String url = this.BASE_URL + CLUSTER_STATS;
        try {
            String response = httpUtil.get(url);
            return new JSONObject(response);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }



}
