package com.dashu.log.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/13 上午10:35
 **/
@Entity
@Table(name = "message_alter_conf", schema = "logmonitor")
public class MessageAlterConf {
    private int id;
    private String alterLevel;
    private String alterWay;
    private int isShow;
    private int frequency;
    private String chargeMan;
    private String messageAlterName;

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
    @Column(name = "alter_way")
    public String getAlterWay() {
        return alterWay;
    }

    public void setAlterWay(String alterWay) {
        this.alterWay = alterWay;
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
    @Column(name = "message_alter_name")
    public String getMessageAlterName() {
        return messageAlterName;
    }

    public void setMessageAlterName(String messageAlterName) {
        this.messageAlterName = messageAlterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageAlterConf that = (MessageAlterConf) o;
        return id == that.id &&
                isShow == that.isShow &&
                frequency == that.frequency &&
                Objects.equals(alterLevel, that.alterLevel) &&
                Objects.equals(alterWay, that.alterWay) &&
                Objects.equals(chargeMan, that.chargeMan) &&
                Objects.equals(messageAlterName, that.messageAlterName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, alterLevel, alterWay, isShow, frequency, chargeMan, messageAlterName);
    }
}
