package com.dashu.log.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午2:47
 **/
@Entity
@Table(name = "self_alter_history", schema = "logmonitor")
public class SelfAlterHistory {
    private int id;
    private String alterName;
    private String alterWay;
    private String chargeMan;
    private int isShow;
    private String content;
    private Timestamp alterTime;
    private int isHandle;
    private String alterType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "alter_name")
    public String getAlterName() {
        return alterName;
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
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
    @Column(name = "charge_man")
    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    @Basic
    @Column(name = "is_show")
    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "alter_time")
    public Timestamp getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(Timestamp alterTime) {
        this.alterTime = alterTime;
    }

    @Basic
    @Column(name = "is_handle")
    public int getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(int isHandle) {
        this.isHandle = isHandle;
    }

    @Basic
    @Column(name = "alter_type")
    public String getAlterType() {
        return alterType;
    }

    public void setAlterType(String alterType) {
        this.alterType = alterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelfAlterHistory that = (SelfAlterHistory) o;
        return id == that.id &&
                isShow == that.isShow &&
                isHandle == that.isHandle &&
                Objects.equals(alterName, that.alterName) &&
                Objects.equals(alterWay, that.alterWay) &&
                Objects.equals(chargeMan, that.chargeMan) &&
                Objects.equals(content, that.content) &&
                Objects.equals(alterTime, that.alterTime) &&
                Objects.equals(alterType, that.alterType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, alterName, alterWay, chargeMan, isShow, content, alterTime, isHandle, alterType);
    }
}
