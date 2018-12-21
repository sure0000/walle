package com.dashu.log.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/12 下午4:30
 **/
@Entity
@Table(name = "metric_conf", schema = "logmonitor")
public class MetricConf {
    private int id;
    private String alterLevel;
    private int metricThreshold;
    private String alterWay;
    private String isShow;
    private int frequency;
    private String chargeMan;
    private String metricName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "alter_level")
    public String getAlterLevel() {
        return alterLevel;
    }

    public void setAlterLevel(String alterLevel) {
        this.alterLevel = alterLevel;
    }

    @Basic
    @Column(name = "metric_threshold")
    public int getMetricThreshold() {
        return metricThreshold;
    }

    public void setMetricThreshold(int metricThreshold) {
        this.metricThreshold = metricThreshold;
    }

    @Basic
    @Column(name = "alter_way")
    public String getAlterWay() {
        return alterWay;
    }

    public void setAlterWay(String alterWay) {
        this.alterWay = alterWay;
    }

    @Basic
    @Column(name = "is_show")
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "frequency")
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Basic
    @Column(name = "charge_man")
    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    @Basic
    @Column(name = "metric_name")
    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricConf that = (MetricConf) o;
        return id == that.id &&
                metricThreshold == that.metricThreshold &&
                frequency == that.frequency &&
                Objects.equals(alterLevel, that.alterLevel) &&
                Objects.equals(alterWay, that.alterWay) &&
                Objects.equals(isShow, that.isShow) &&
                Objects.equals(chargeMan, that.chargeMan) &&
                Objects.equals(metricName, that.metricName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, alterLevel, metricThreshold, alterWay, isShow, frequency, chargeMan, metricName);
    }
}
