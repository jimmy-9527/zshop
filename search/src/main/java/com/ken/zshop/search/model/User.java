package com.ken.zshop.search.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    public User(int id, String name, String address, Date birthday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    private int id;
    private String name;
    private String address;
    private Date birthday;
}
