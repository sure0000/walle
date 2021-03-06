package com.dashu.log.entity;

import java.util.Date;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/4 下午2:26
 **/
public class ErrorLogType {
    private String indexName;
    private String message;
    private String monitorField;
    private String hostname;
    private Integer isForbid;
    private String createdTime;
    private String latestTime;

    public String getIndexName() {
        return indexName;
    }

    public String getMessage() {
        return message;
    }

    public String getMonitorField() {
        return monitorField;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getIsForbid() {
        return isForbid;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMonitorField(String monitorField) {
        this.monitorField = monitorField;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setIsForbid(Integer isForbid) {
        this.isForbid = isForbid;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public ErrorLogType() {
    }

    public ErrorLogType(String indexName, String message, String monitorField, String hostname, Integer isForbid, String createdTime, String latestTime) {
        this.indexName = indexName;
        this.message = message;
        this.monitorField = monitorField;
        this.hostname = hostname;
        this.isForbid = isForbid;
        this.createdTime = createdTime;
        this.latestTime = latestTime;
    }

    @Override
    public String toString() {
        return "ErrorLogType{" +
                "indexName='" + indexName + '\'' +
                ", message='" + message + '\'' +
                ", monitorField='" + monitorField + '\'' +
                ", hostname='" + hostname + '\'' +
                ", isForbid=" + isForbid +
                ", createdTime=" + createdTime +
                ", latestTime=" + latestTime +
                '}';
    }
}
