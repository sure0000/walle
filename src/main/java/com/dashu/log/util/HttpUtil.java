package com.dashu.log.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * @Description http相关请求封装
 * @Author: xuyouchang
 * @Date 2018/11/1 上午11:15
 **/
public class HttpUtil {
    private static Logger logger = null;

    //构造函数
    public HttpUtil(Object o){
        this.logger = LoggerFactory.getLogger(o.getClass());
    }

    /**
     * POST请求处理
     * @param url
     * @param message
     * @return
     */
    public boolean post(String url,String message){

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(message,"utf-8");
        httpPost.setEntity(entity);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        HttpClient httpClient = HttpClients.createDefault();
        try {
            httpClient.execute(httpPost);
            ((CloseableHttpClient) httpClient).close();
            return true;
        } catch (IOException e) {
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * GET请求处理
     * @param url
     * @return 请求结果
     */
    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = null;
        String line;
        String result = "";
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    entity.getContent(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                result = result + line + "\n";
            }
            ((CloseableHttpClient) client).close();
        return result;
    }
}
