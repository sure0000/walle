package com.dashu.log.filter;

import com.dashu.log.alter.dao.AlterHistoryRepository;
import com.dashu.log.dao.ErrorLogTypeRepository;
import com.dashu.log.dao.FilterErrorTypeRepository;

import com.dashu.log.entity.ErrorLogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description errorLog过滤
 * @Author: xuyouchang
 * @Date 2018/11/22 下午7:42
 **/
@Service
public class DocFilter {
    private static final Logger logger = LoggerFactory.getLogger(DocFilter.class);
    private static final int TIME_THRESHOLD = 5;

    @Resource
    private ErrorLogTypeRepository errorLogTypeRepository;
    @Autowired
    private FilterErrorTypeRepository filterErrorTypeRepository;
    @Autowired
    private AlterHistoryRepository alterHistoryRepository;

    /**
     * 判断是否过滤消息
     * @param errorLogType
     * @param errorLog
     * @return
     */
    public boolean isFilter(ErrorLogType errorLogType, String errorLog){
        if (isForbid(errorLogType)||checkFrequency(errorLogType)||checkLength(errorLog)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断errorlog type是否已被禁止
     * @param errorLogType
     * @return
     */
    public boolean isForbid(ErrorLogType errorLogType){
//        List<Integer> forbidErrorType=filterErrorTypeRepository.findAllErrorTypeId();
//        for(int id : forbidErrorType){
//            if (id==errorLogType.getId()){
//                logger.info("error type "+id+" is forbid!");
//                return true;
//            }
//        }
        return false;
    }

    /**
     * 检查errorLog出现频率
     * @param errorLogType
     * @return
     */
    public boolean checkFrequency(ErrorLogType errorLogType){
        Date curTime = new Date(System.currentTimeMillis());
        String lastUpdateTime= errorLogType.getLatestTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            long timeInterval=(curTime.getTime()-sdf.parse(lastUpdateTime).getTime())/1000/60;
            if(timeInterval>TIME_THRESHOLD){
                return false;
            }else{
                return true;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return false;
        }

    }


    /**
     * errorLog长度检查
     * @param errorLog
     * @return
     */
    public boolean checkLength(String errorLog){
        if (errorLog.length()<100){
            return true;
        }else{
            return false;
        }
    }


}
