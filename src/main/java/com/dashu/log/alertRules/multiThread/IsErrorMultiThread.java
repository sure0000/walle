package com.dashu.log.alertRules.multiThread;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.alertRules.ESIndexRule;
import com.dashu.log.dao.ErrorLogTypeIndex;
import com.dashu.log.dao.ErrorLogTypeRepository;
import com.dashu.log.entity.ErrorLogType;
import com.dashu.log.filter.DocFilter;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 日志分析线程
 * @Author: xuyouchang
 * @Date 2018/11/29 上午9:49
 **/
public class IsErrorMultiThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(IsErrorMultiThread.class);
    private List<Map> latestDoc;
    private ErrorLogTypeRepository errorLogTypeRepository;
    private DocFilter docFilter;
    private ESIndexRule esIndexRule;
    private Integer SCORE_THRESHOLD;

    public IsErrorMultiThread(List<Map> latestDoc, ErrorLogTypeRepository errorLogTypeRepository, DocFilter docFilter, ESIndexRule esIndexRule, Integer scoreThreshold){
        this.latestDoc = latestDoc;
        this.errorLogTypeRepository = errorLogTypeRepository;
        this.docFilter = docFilter;
        this.esIndexRule = esIndexRule;
        this.SCORE_THRESHOLD = scoreThreshold;
    }

    public void run(){
        WalleNotify notify = new WalleNotify();
        for (Map map : this.latestDoc){
            String message=map.get("message").toString();
            String loglevel=map.get("loglevel").toString();
            Map host=(Map)map.get("host");
            String hostname=host.get("name").toString();
            Map<String,Object> fields=(Map)map.get("fields");
            String topic=fields.get("log_topic").toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            ErrorLogTypeIndex errorLogTypeIndex = new ErrorLogTypeIndex();
            SearchHits hitsObject = errorLogTypeIndex.getErrorLogType(message);
            if (hitsObject == null){

                break;
            }
            float maxScore =hitsObject.getMaxScore();
            if ( String.valueOf(maxScore) == "NaN"){

                 maxScore = -1;
            }else{

                 maxScore = hitsObject.getMaxScore();
            }
            if (maxScore <= this.SCORE_THRESHOLD){
//                message=message.replace("\n","\\n");
                ErrorLogType errorLogType = new ErrorLogType(topic,message,loglevel,hostname,0,sdf.format(new Date()),sdf.format(new Date()));
                errorLogTypeIndex.insertToIndex(errorLogType);
                message = message.replace("\n","\\n");
                notify.sendMessage(topic,message,topic);

            }else {
                SearchHit hit = hitsObject.getHits()[0];
                Map source = hit.getSourceAsMap();
                String docId = hit.getId();
                String Message = source.get("Message").toString();
                String IndexName = source.get("IndexName").toString();
                String MonitorField = source.get("MonitorField").toString();
                String HostName = source.get("HostName").toString();
                int IsForbid = Integer.parseInt(source.get("IsForbid").toString());
                String createdTime = source.get("CreatedTime").toString();
                String latestTime = source.get("LatestUpdateTime").toString();
                try {
                    ErrorLogType errorLogType = new ErrorLogType(IndexName,Message,MonitorField, HostName,IsForbid,createdTime,latestTime);
                    boolean flag = this.docFilter.isFilter(errorLogType,message);
                    if (flag){
                        continue;
                    }else {
                        Date date = new Date();
                        errorLogTypeIndex.updateSingleField(docId,"LatestUpdateTime",sdf.format(date));
                        message = "docId:"+docId+"\\n"+message.replace("\n","\\n");
                        notify.sendMessage(topic,message,topic);
                    }
                } catch ( Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
