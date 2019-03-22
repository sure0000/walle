package com.dashu.log.alertRules;

import com.dashu.log.monitor.node.Node;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/21 下午5:10
 **/
class ESNodeRuleTest {

    @Test
    void checkALL() {
        Node node = new Node("http://elastic:elastic@idces2:9200/");
        for (JSONObject nodeInfoObject: node.getNodesInfo()) {
            ESNodeRule esNodeRule = new ESNodeRule("http://elastic:elastic@idces2:9200/",nodeInfoObject);
            esNodeRule.checkFs(90);
            esNodeRule.checkIndex();
            esNodeRule.checkJVMHeap(90);
            esNodeRule.checkOsMem(90);
        }


    }
}