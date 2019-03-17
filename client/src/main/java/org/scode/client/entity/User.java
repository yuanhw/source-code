package org.scode.client.entity;

import lombok.Data;

/**
 * @author wyh
 * @date 2019/3/17 12:39 PM
 */
@Data
public class User {
    private int id;

    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
