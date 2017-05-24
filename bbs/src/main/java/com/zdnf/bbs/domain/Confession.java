package com.zdnf.bbs.domain;

/**
 * Created by 90488 on 2017/5/23.
 */
public class Confession {
    private int id;        // id
    private String name;   // 表白者
    private String lover;  // 表白对象
    private String telnum; // 表白对象联系方式
    private String content;// 表白内容
    private int fate;      // 缘分值
    private String time;   // 时间

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLover() {
        return lover;
    }
    public void setLover(String lover) {
        this.lover = lover;
    }
    public String getTelnum() {
        return telnum;
    }
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getFate() {
        return fate;
    }
    public void setFate(int fate) {
        this.fate = fate;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
