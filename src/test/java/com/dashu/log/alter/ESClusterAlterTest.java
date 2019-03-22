package com.dashu.log.alter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/21 上午10:14
 **/
class ESClusterAlterTest {

    @Test
    void alter() {
        ESClusterAlert esClusterAlert = new ESClusterAlert("http://elastic:elastic@idces1:9200/", 90, 90,90);
        esClusterAlert.alert();
    }
}