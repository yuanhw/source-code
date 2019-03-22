package org.scode.m2.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wyh
 * @date 2019/3/21 8:05 PM
 * @description
 */
@Data
public class User {

    private long id;

    private String name;

    private int age;

    private long account;

    private Date createTime;

    private Date updateTime;

    public User() {
    }

    public User(long id, String name, int age, long account, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.account = account;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
