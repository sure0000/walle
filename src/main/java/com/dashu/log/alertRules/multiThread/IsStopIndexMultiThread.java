package com.dashu.log.alertRules.multiThread;

import com.dashu.log.alter.WalleNotify;
import com.dashu.log.client.dao.IndexConfRepository;
import com.dashu.log.entity.IndexConf;
import com.dashu.log.util.HttpUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/5 下午3:29
 **/
public class IsStopIndexMultiThread extends Thread {
    private Thread t;
    private static Logger logger = LoggerFactory.getLogger(IsStopIndexMultiThread.class);
    private IndexConf INDEX_CONF;
    private String INDEX_NAME;
    private String BASE_URL = "http://elastic:elastic@es1:9200/";
    private IndexConfRepository indexConfRepository;

    public IsStopIndexMultiThread (IndexConf indexConf,IndexConfRepository indexConfRepository){
        this.INDEX_CONF = indexConf;
        this.INDEX_NAME = indexConf.getIndexName();
        this.indexConfRepository = indexConfRepository;
    }

    public void run(){
        HttpUtil httpUtil = new HttpUtil(this);
        String url = this.BASE_URL+this.INDEX_NAME+"/_stats/indexing";
        try {
            String result = httpUtil.get(url);
            JSONObject resultObject = new JSONObject(result);
            JSONObject allObject = new JSONObject(resultObject.get("_all").toString());
            JSONObject totalObject = new JSONObject(allObject.get("total").toString());
            JSONObject indexingObject = new JSONObject(totalObject.get("indexing").toString());
            long index_total = Long.valueOf(indexingObject.get("index_total").toString());

            Date curTime = new Date();
            long scan_time = Long.valueOf(this.INDEX_CONF.getScanTime()).longValue();
            long timeInterval;
            if (scan_time!=0){      //判断是否首次扫描
                timeInterval = (curTime.getTime()-scan_time)/1000/60;
                if (timeInterval>this.INDEX_CONF.getScanInterval()){                //判断距上次扫描时间间隔
                    indexConfRepository.updateScanTime(String.valueOf(curTime.getTime()),this.INDEX_NAME);
                    long diff = index_total - Long.valueOf(this.INDEX_CONF.getIndexTotal());      //判断index中的文档数是否增加
                    if (diff <= 0){
                        WalleNotify notify = new WalleNotify();
                        notify.sendMessage("stop indexing",this.INDEX_NAME+" is stop indexing"+" original num is:"+this.INDEX_CONF.getIndexTotal()+" now is:"+index_total,"stop indexing");
                    }else {
                        indexConfRepository.updateIndexTotalNum(String.valueOf(index_total),this.INDEX_NAME);   //更新文档数
                    }
                }
            }else {
                indexConfRepository.updateScanTime(String.valueOf(curTime.getTime()),this.INDEX_NAME);
                long diff = index_total - Integer.parseInt(this.INDEX_CONF.getIndexTotal());      //判断index中的文档数是否增加

                if (diff <= 0){
                    WalleNotify notify = new WalleNotify();
                    notify.sendMessage("stop indexing",this.INDEX_NAME+" is stop indexing"+" index_total is:"+index_total,"stop indexing");
                }else {
                    indexConfRepository.updateIndexTotalNum(String.valueOf(index_total),this.INDEX_NAME);   //更新文档数
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start () {
        logger.info("the thread "+this.INDEX_NAME+" is start");
        if (t == null) {
            t = new Thread (this, this.INDEX_NAME);
            t.start ();
        }
    }
}
