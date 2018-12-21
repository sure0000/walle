package com.dashu.log.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/18 下午1:57
 **/
@Entity
@Table(name = "user", schema = "logMonitor")
public class User {
    private int id;
    private String username;
    private String password;
    private String department;
    private int level;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                level == user.level &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(department, user.department);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, department, level);
    }
}
