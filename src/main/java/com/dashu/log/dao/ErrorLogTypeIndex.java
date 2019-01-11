package com.dashu.log.dao;

import com.dashu.log.entity.ErrorLogType;
import com.dashu.log.monitor.ESUtil;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 错误日志类型索引
 * @Author: xuyouchang
 * @Date 2018/12/4 上午10:32
 **/
public class ErrorLogTypeIndex {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLogTypeIndex.class);
    private final String INDEX = "error_log_type_2018-12-07";
    private final String FIELD = "Message:";
    private ErrorLogTypeIndex errorLogTypeIndex;
    private String URL = "http://elastic:elastic@es1:9200/"+INDEX+"/_search?q="+FIELD;


    /**
     * 匹配相似日志
     * @param message
     * @return
     */
    public SearchHits getErrorLogType(String message){
//        message = filterMassage(message);
        ESUtil esUtil = ESUtil.getInstance();
        RestHighLevelClient client = esUtil.connect();
        SearchRequest request = new SearchRequest(this.INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("Message", message)
                .fuzziness(Fuzziness.AUTO);
        sourceBuilder.query(matchQueryBuilder);
        request.source(sourceBuilder);
        try {
            SearchResponse response = client.search(request);
            SearchHits hits = response.getHits();
            return hits;
        } catch (IOException e) {
            logger.error(e.toString());
            return null;
        }
    }

    /**
     * update isFofbid
     * @param docId
     * @return
     */
    public  boolean updateSingleField(String docId,String field,String content){
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put(field,content);
        UpdateRequest request = new UpdateRequest("error_log_type","doc",docId).doc(jsonMap);
        ESUtil esUtil = ESUtil.getInstance();
        RestHighLevelClient client = esUtil.connect();
        try {
            UpdateResponse response = client.update(request);
            client.close();
            return true;
        } catch (IOException e) {
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * 插入error log type
     * @param errorLogType
     * @return
     */
    public boolean insertToIndex(ErrorLogType errorLogType){
        ESUtil esUtil = ESUtil.getInstance();
        RestHighLevelClient client = esUtil.connect();

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("CreatedTime",errorLogType.getCreatedTime());
        jsonMap.put("HostName",errorLogType.getHostname());
        jsonMap.put("IndexName",errorLogType.getIndexName());
        jsonMap.put("IsForbid",errorLogType.getIsForbid());
        jsonMap.put("LatestUpdateTime",errorLogType.getLatestTime());
        jsonMap.put("Message",errorLogType.getMessage());
        jsonMap.put("MonitorField",errorLogType.getMonitorField());

        IndexRequest indexRequest = new IndexRequest("error_log_type","doc").source(jsonMap);
        try {
            IndexResponse response = client.index(indexRequest);
            return true;
        } catch (IOException e) {
            logger.error(e.toString());
            return false;
        }

    }

    /**
     * es查询非法字符过滤
     * @param message
     * @return
     */
    public static String filterMassage(String message){
        message = message.split("\n")[0];
        return message;

    }
}
