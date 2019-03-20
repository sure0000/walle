package com.dashu.log.alterRules;

import com.dashu.log.monitor.cluster.Cluster;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/20 下午5:25
 **/
class ESClusterRuleTest {

    @Test
    void checkCluster() {
        Cluster cluster = new Cluster("http://elastic:elastic@idces1:9200/");
        JSONObject clusterObject = cluster.getClusterInfo();
        JSONObject nodesObject = cluster.getClusterNodes(clusterObject);

        ESClusterRule esClusterRule = new ESClusterRule();
        JSONObject heapObject = esClusterRule.checkJvmHeap(nodesObject,90);
        JSONObject memObject = esClusterRule.checkOsMem(nodesObject,90);
        boolean flag = esClusterRule.isClusterHealth(clusterObject.getString("status"));

    }

}