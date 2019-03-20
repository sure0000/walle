package com.dashu.log.monitor.index;

import com.dashu.log.util.HttpUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Description 获取index相关状态信息
 * @Author: xuyouchang
 * @Date 2019/3/20 下午3:30
 **/
public class Index {
    private static final String INDEX_STATS = "_stats";
    private String BASE_URL;
    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    public Index(String baseUrl){
        this.BASE_URL = baseUrl;
    }

    /** 获取 index segment 状态信息 **/
    public JSONObject getSegments(JSONObject primariesObject) {
        JSONObject segmentObject = primariesObject.getJSONObject("segments");
        return segmentObject;
    }

    /** 获取 index 查询缓存状态 **/
    public JSONObject getQueryCache(JSONObject primariesObject) {
        JSONObject queryCacheObject = primariesObject.getJSONObject("query_cache");
        return queryCacheObject;
    }

    /** 获取 index 写入状态 **/
    public JSONObject getIndexing(JSONObject primariesObject) {
        JSONObject indexingObject = primariesObject.getJSONObject("indexing");
        return indexingObject;
    }

    /** 获取 index 主分片信息 **/
    public JSONObject getPrimaries(JSONObject indexObject, String index) {
        JSONObject indicesobject = indexObject.getJSONObject("indices").getJSONObject(index);
        JSONObject primariesObject = indicesobject.getJSONObject("primaries");
        return primariesObject;
    }

    /** 获取 Index 状态相关信息 **/
    public JSONObject getIndexInfo(String index) {
        HttpUtil httpUtil = new HttpUtil(Index.class);
        String url = this.BASE_URL + index + "/"+INDEX_STATS;
        try {
            String response = httpUtil.get(url);
            return new JSONObject(response);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


}
