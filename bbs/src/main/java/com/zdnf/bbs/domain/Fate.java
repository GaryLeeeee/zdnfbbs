package com.zdnf.bbs.domain;

/**
 * Created by 90488 on 2017/5/21.
 */
public class Fate {
    private int id;         // id
    private String fateid;  // 被点赞id
    private String user;    // 点赞者id
    private boolean state; // 点赞状态

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFateid() {
        return fateid;
    }
    public void setFateid(String fateid) {
        this.fateid = fateid;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
}
