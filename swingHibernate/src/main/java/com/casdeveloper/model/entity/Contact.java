package com.casdeveloper.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

//    @GenericGenerator(
//            name = "SEQ_CONTACT",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "SEQ_CONTACT"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
//    )

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_CONTACT")
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
