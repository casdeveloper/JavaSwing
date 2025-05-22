package com.casdeveloper.model;

import java.sql.Date;

public class Contact {

    private Long id;
    private String name;
    private String nickName;
    private Date birth_Day;

    public Contact(Long id, String name, String nickName, Date birth_Day) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.birth_Day = birth_Day;
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirth_Day() {
        return birth_Day;
    }

    public void setBirth_Day(Date birth_Day) {
        this.birth_Day = birth_Day;
    }
}
