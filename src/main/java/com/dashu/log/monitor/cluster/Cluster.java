package com.dashu.log.monitor.cluster;

import com.dashu.log.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @Description es集群级别服务指标监控
 * @Author: xuyouchang
 * @Date 2018/11/1 上午10:39
 **/
public class Cluster {
    private static String BASE_URL = "http://elastic:elastic@es1:9200/";
    private static final Logger logger = LoggerFactory.getLogger(Cluster.class);
    private static final String[] nodeIdList = {"esnode-1","esnode-2","esnode-3"};

    /**
     * 节点拒绝数检测
     * @return
     */
    public String reject(){
        HttpUtil httpUtil = new HttpUtil(this);
        int bulkRejected;
        int indexRejected;
        List<String> retList = new ArrayList<>();
        String url = BASE_URL+"_nodes/stats";
        try{
            String result = httpUtil.get(url);
            JSONObject resultObject = new JSONObject(result);
            JSONObject nodeObject = new JSONObject(resultObject.get("nodes").toString());
            Iterator<String> iterable = nodeObject.keys();

            while (iterable.hasNext()){
                String key = iterable.next();
                String value = nodeObject.get(key).toString();
                JSONObject statObject = new JSONObject(value);
                String nodename = statObject.get("name").toString();
                JSONObject threadPoolObject = new JSONObject(statObject.get("thread_pool").toString());
                JSONObject indexObject = new JSONObject(threadPoolObject.get("index").toString());
                indexRejected = Integer.parseInt(indexObject.get("rejected").toString());
                if (indexRejected != 0){
                    String ret = nodename+":"+"index rejected("+indexRejected+")";
                    retList.add(ret);
                }

            }
        }catch (IOException e){
            logger.error("node stat request fail!"+e.toString());
            return "node stat request fail!";
        }

        if (retList.size() == 0){
            return "no reject";
        }else{
            return retList.toString();
        }

    }

    /**
     * 集群健康监控
     * @return
     */
    public  boolean isHealth() {
        String URL = BASE_URL+"_cluster/health";
        HttpUtil httpUtil = new HttpUtil(this);
        try {
            String clusterHealth = httpUtil.get(URL);
            JSONObject healthObject = new JSONObject(clusterHealth);
            String status = healthObject.get("status").toString();
            if (status.equals("green")){
                return true;
            }else{
                return false;
            }

        } catch (IOException e) {
            logger.error(e.toString());
            return false;
        }

    }

}
