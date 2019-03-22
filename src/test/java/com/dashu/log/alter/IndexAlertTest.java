package com.dashu.log.alter;

import com.dashu.log.util.HttpUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/3/21 上午11:22
 **/
class IndexAlertTest {

    @Test
    void alert() {
        IndexAlert indexAlert = new IndexAlert("http://elastic:elastic@idces1:9200/");
        indexAlert.alert();
    }

    @Test
    void getAllIndices() {
        HttpUtil httpUtil = new HttpUtil(this);
        try {
            String result = httpUtil.get("http://elastic:elastic@idces1:9200/_cat/indices");
            List<String> indexList = new ArrayList<>();
            String[] resultArry = result.split("\n");
            for (String line: resultArry){
                String[] lineArray = line.split(" ");
                indexList.add(lineArray[2]);
                System.out.println(lineArray[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}