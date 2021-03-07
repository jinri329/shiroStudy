package com.zyy.shirodemo.bean;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String passWord;
    private String nickName;

    public User() {
    }

    public User(Integer id, String userName, String passWord, String nickName) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
