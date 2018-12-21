package com.dashu.log.alterRules;

import com.dashu.log.alterRules.multiThread.IsErrorMultiThread;
import com.dashu.log.alterRules.multiThread.IsStopIndexMultiThread;
import com.dashu.log.dao.ErrorLogTypeRepository;
import com.dashu.log.client.dao.IndexConfRepository;
import com.dashu.log.entity.IndexConf;
import com.dashu.log.filter.DocFilter;
import com.dashu.log.monitor.index.GetLatestDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description document 监控
 * @Author: xuyouchang
 * @Date 2018/11/23 上午9:39
 **/
@Service
public class ESIndexRule {
    private static final Logger logger = LoggerFactory.getLogger(ESIndexRule.class);
    private static final double SIMILARITY_RATIO = 0.7;
    @Resource
    private ErrorLogTypeRepository errorLogTypeRepository;
    @Resource
    private DocFilter docFilter;
    @Resource
    private GetLatestDocument latestDocument;
    @Resource
    private IndexConfRepository indexConfRepository;


    /**
     * index是否停止插入
     */
    public void isStopIndex(){
        List<IndexConf> indexConfList = indexConfRepository.getAllIndexConf();
        for (IndexConf indexConf : indexConfList){
            IsStopIndexMultiThread isStopIndexMultiThread = new IsStopIndexMultiThread(indexConf,indexConfRepository);
            isStopIndexMultiThread.start();
        }
    }
    /**
     * 是否出现error等级日志
     */
    public void isError(){
        List<IndexConf> indexConfList = indexConfRepository.getAllIndexConf();
        for (IndexConf indexConf:indexConfList){
            List<Map> latestDoc = latestDocument.getLatestDoc(indexConf);
            if (latestDoc == null){
                continue;
            }else {
                IsErrorMultiThread indexMultiThread = new IsErrorMultiThread(latestDoc,errorLogTypeRepository,docFilter,this,0);
                indexMultiThread.start();
            }

        }

    }



//    /**
//     * 识别信息是否为已知类型
//     * @param message
//     * @return
//     */
//    public ErrorLogTypeBak identifyErrorType(String message){
//        String comparedMessage=message.split("\n")[0];
//        List<ErrorLogTypeBak> errorLogTypesList=errorLogTypeRepository.findAll();
//
//        //查看是否是已存在异常
//        for(ErrorLogTypeBak errorLogType: errorLogTypesList){
//            String[] keywords=errorLogType.getKeywords().split(" ");
//            int positive=0;
//            int negative=0;
//            for(int i=0;i<keywords.length;i++){
//                if(filterInvalidWord(keywords[i])==false){
//                    negative++;
//                }else{
//                    if(comparedMessage.contains(keywords[i])) {
//                        positive++;
//                    }
//                }
//
//            }
//            double similarityRatio=(double) positive/(keywords.length-negative);
//            if (similarityRatio>this.SIMILARITY_RATIO){
//                //识别出已有异常,返回
//                logger.info("已有类型:"+errorLogType.getKeywords()+"相似度："+similarityRatio);
//                return errorLogType;
//            }
//        }
//        return null;
//    }

//    /**
//     * 关键信息提取与分词
//     * @param message
//     * @return
//     */
//    public List<String> splitString(String message){
//        List<String> wordList=new ArrayList<>();
//        String keywords=message.split("\n")[0];
//        String[] getInfo=keywords.split(" ");
//        for(int i=0;i<getInfo.length;i++){
//            wordList.add(getInfo[i]);
//        }
//        return wordList;
//    }

//    /**
//     * 过滤无效字符
//     * @param words
//     * @return
//     */
//    public static boolean filterInvalidWord(String words){
//        //替换特殊字符
//        words=words.replace("[","");
//        words=words.replace("]","");
//        words=words.replace("+","");
//
//        String pattern="^(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)$";
//        String pattern2="^(\\d\\d):(\\d\\d):(\\d\\d),(\\d\\d\\d|\\d\\d)$";
//        String pattern3="^(\\d\\d\\d\\d)/(\\d\\d)/(\\d\\d)$";
//        String pattern4="^(\\d\\d)/(\\S\\S\\S)/(\\d\\d\\d\\d)$";
//        String pattern5="^(\\d\\d\\d\\d)$";
//        if (Pattern.matches(pattern,words)||Pattern.matches(pattern2,words)
//                ||Pattern.matches(pattern3,words)||Pattern.matches(pattern4,words)
//                ||Pattern.matches(pattern5,words)){
//            return false;
//        }
//        if (words=="ERROR"||words=="error"){
//            return false;
//        }else {
//            return true;
//        }
//    }

}
