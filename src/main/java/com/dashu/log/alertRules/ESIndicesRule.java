package com.dashu.log.alertRules;

import org.json.JSONObject;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/21 上午10:21
 **/
public class ESIndicesRule {

    /** 检查索引插入失败数量 **/
    public JSONObject checkIndexing(JSONObject primariesObject) {
        int indexingFailedNum = primariesObject.getJSONObject("indexing").getInt("index_failed");
        int isAlert = 0;

        if (indexingFailedNum > 0) {
            isAlert = 1;
        }

        JSONObject checkResult = new JSONObject();
        checkResult.put("isAlert", isAlert);
        checkResult.put("indexingFailedNum", indexingFailedNum);
        checkResult.put("desc", "index 失败数量");

        return checkResult;
    }
}
