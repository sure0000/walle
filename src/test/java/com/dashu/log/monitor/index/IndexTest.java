package com.dashu.log.monitor.index;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/20 下午4:02
 **/
class IndexTest {

    @Test
    void getPrimaries() {
        Index index = new Index("http://elastic:elastic@idces1:9200/");
        JSONObject indexObject = index.getIndexInfo("ticket-support-faq-v5");
        JSONObject priobject = index.getPrimaries(indexObject,"ticket-support-faq-v5");
        index.getIndexing(priobject);
        index.getQueryCache(priobject);
        index.getSegments(priobject);
    }
}