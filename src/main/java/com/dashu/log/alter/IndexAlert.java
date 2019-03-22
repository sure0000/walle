package com.dashu.log.alter;

import com.dashu.log.alertRules.ESIndicesRule;
import com.dashu.log.monitor.index.Index;
import com.dashu.log.util.HttpUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description index告警
 * @Author: xuyouchang
 * @Date 2018/11/22 下午5:26
 **/

public class IndexAlert {
    private String BASE_URL;
    private static final String ALTER_NAME = "ES Index";
    private static final String CAT_INDICES = "_cat/indices";
    private static final Logger logger = LoggerFactory.getLogger(IndexAlert.class);

    public IndexAlert(String baseUrl) {
        this.BASE_URL = baseUrl;
    }
    /**
     * 告警
     */
    public void alert(){
        Index indexStatus = new Index(this.BASE_URL);
        List<String> indexList = getAllIndices();
        ESIndicesRule esIndicesRule = new ESIndicesRule();
        Map<String,Integer> indexFailedMap = new HashMap<>();

        for (String index : indexList) {
            JSONObject indexStatusObject = indexStatus.getIndexInfo(index);
            JSONObject primariesObject = indexStatus.getPrimaries(indexStatusObject, index);
            JSONObject checkResult = esIndicesRule.checkIndexing(primariesObject);

            if (checkResult.getInt("isAlert") == 1) {
                indexFailedMap.put(index, checkResult.getInt("indexingFailedNum"));
            }
        }

        String info = "the failed index and num is: \n" + indexFailedMap.toString();
        WalleNotify notify = new WalleNotify();
        notify.sendMessage(ALTER_NAME, info, "index");

    }

    /** 获取所有索引 **/
    public List<String> getAllIndices() {
        HttpUtil httpUtil = new HttpUtil(this);
        try {
            String url = this.BASE_URL + CAT_INDICES;
            String indices = httpUtil.get(url);
            String[] indicesArray = indices.split("\n");
            List<String> indexList = new ArrayList<>();

            for (String index : indicesArray) {
                String[] indexArray = index.split(" ");
                indexList.add(indexArray[2]);
            }

            return indexList;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
