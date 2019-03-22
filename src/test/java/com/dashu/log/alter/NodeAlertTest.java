package com.dashu.log.alter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/22 上午9:42
 **/
class NodeAlertTest {

    @Test
    void alert() {
        NodeAlert nodeAlert = new NodeAlert("http://elastic:elastic@idces1:9200/",90,90,90);
        nodeAlert.alert();
    }
}