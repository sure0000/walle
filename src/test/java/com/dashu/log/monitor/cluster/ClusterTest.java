package com.dashu.log.monitor.cluster;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/20 下午1:49
 **/
class ClusterTest {

    @Test
    void getClusterInfo() {
        Cluster cluster = new Cluster("http://elastic:elastic@idces1:9200/");
        JSONObject jsonObject = cluster.getClusterInfo();
    }

    @Test
    void getClusterIndices() {
        Cluster cluster = new Cluster("http://elastic:elastic@idces1:9200/");
        JSONObject jsonObject = cluster.getClusterInfo();
        JSONObject indicesObject = cluster.getClusterIndices(jsonObject);
    }

    @Test
    void getClusterNodes() {
        Cluster cluster = new Cluster("http://elastic:elastic@idces1:9200/");
        JSONObject jsonObject = cluster.getClusterInfo();
        JSONObject nodesObject = cluster.getClusterNodes(jsonObject);
    }

    @Test
    void getClusterStatus() {
        Cluster cluster = new Cluster("http://elastic:elastic@idces1:9200/");
        JSONObject jsonObject = cluster.getClusterInfo();
        String status = cluster.getClusterStatus(jsonObject);
    }
}