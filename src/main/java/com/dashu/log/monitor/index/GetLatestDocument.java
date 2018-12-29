package com.dashu.log.monitor.index;

import com.dashu.log.client.dao.IndexConfRepository;
import com.dashu.log.entity.IndexConf;

import com.dashu.log.monitor.dao.QueryHistoryRepository;
import com.dashu.log.util.ReadConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 获取监控index的最新document
 * @Author: xuyouchang
 * @Date 2018/11/22 下午7:06
 **/
@Service
public class GetLatestDocument {
    private static final Logger logger = LoggerFactory.getLogger(GetLatestDocument.class);

    @Resource
    private QueryHistoryRepository queryHistoryRepository;

    /**
     * 获取最新document，并更新index的检测时间点
     *
     * @return
     */
    public List<Map> getLatestDoc(IndexConf indexConf){
        ESQuery esQuery = new ESQuery();
        String index = indexConf.getIndexName();
        String field = indexConf.getFiled();
        String keyword = indexConf.getKeywords();
        if (field == null){
            return null;
        }else{
            String timeCursor=queryHistoryRepository.findOldestTimestampByIndexName(index); // 获取上次查询时间游标
            if (timeCursor==null||timeCursor==""){
                timeCursor="2018-08-23T10:41:45.230Z";
                queryHistoryRepository.insertQueryHistory(index,timeCursor);
            }
            String latestTimestamp=esQuery.getLatestTime(index);                //获取es记录最新时间
            List<Map> latestDoc=esQuery.filterSearch(index,field,keyword,timeCursor);
            queryHistoryRepository.updateOldTimestampByIndexName(latestTimestamp,index);                //更新时间游标

            return latestDoc;
        }



    }

}
