package com.dashu.log.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/24 下午2:42
 **/
@Entity
@Table(name = "index_conf", schema = "logmonitor", catalog = "")
public class IndexConf {
    private int id;
    private String indexName;
    private String filed;
    private String keywords;
    private String indexTotal;
    private Integer scanInterval;
    private String scanTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "index_name")
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Basic
    @Column(name = "filed")
    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    @Basic
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "index_total")
    public String getIndexTotal() {
        return indexTotal;
    }

    public void setIndexTotal(String indexTotal) {
        this.indexTotal = indexTotal;
    }

    @Basic
    @Column(name = "scan_interval")
    public Integer getScanInterval() {
        return scanInterval;
    }

    public void setScanInterval(Integer scanInterval) {
        this.scanInterval = scanInterval;
    }

    @Basic
    @Column(name = "scan_time")
    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexConf indexConf = (IndexConf) o;
        return id == indexConf.id &&
                Objects.equals(indexName, indexConf.indexName) &&
                Objects.equals(filed, indexConf.filed) &&
                Objects.equals(keywords, indexConf.keywords) &&
                Objects.equals(indexTotal, indexConf.indexTotal) &&
                Objects.equals(scanInterval, indexConf.scanInterval) &&
                Objects.equals(scanTime, indexConf.scanTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, indexName, filed, keywords, indexTotal, scanInterval, scanTime);
    }
}
