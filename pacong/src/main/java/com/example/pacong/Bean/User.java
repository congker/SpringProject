package com.example.pacong.Bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xuwencong on 2019/4/11
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private String username;
    private Integer Age;

    public User(String username, Integer age) {
        this.username = username;
        Age = age;
    }
}
