package com.dashu.log.monitor.node;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/20 下午2:24
 **/
class NodeTest {

    @Test
    void getNodesInfo() {
        Node node = new Node("http://elastic:elastic@idces1:9200/");
        node.getNodesInfo();
    }

    @Test
    void getKPI() {
        Node node = new Node("http://elastic:elastic@idces1:9200/");
        List<JSONObject> nodeobjectList = node.getNodesInfo();
        for (JSONObject nodeObject : nodeobjectList){
            node.getNodeFs(nodeObject);
            node.getNodeHttp(nodeObject);
            node.getNodeIndices(nodeObject);
            node.getNodeJvm(nodeObject);
            node.getNodeOs(nodeObject);
            node.getNodeThreadPool(nodeObject);
        }
    }
}