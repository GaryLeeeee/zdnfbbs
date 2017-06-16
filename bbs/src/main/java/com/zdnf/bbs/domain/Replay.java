package com.zdnf.bbs.domain;

import javax.validation.constraints.NotNull;

/**
 * Created by ZDNF on 2017/5/14.
 */
public class Replay {
    private int id;//自身的id
    @NotNull
    private int father;//所属id
    @NotNull
    private String author;//作者
    @NotNull
    private String content;//正文
    @NotNull
    private String times;//时间

    public void setId(int id){this.id=id;}
    public int getId(){return id;}
    public void setFather(int father){this.father=father;}
    public int getFather(){return father;}
    public void setAuthor(String auth){this.author=auth;}
    public String getAuthor(){return author;}
    public void setContent(String content){this.content=content;}
    public String getContent(){return content;}
    public void setTimes(String time){this.times=time;}
    public String getTimes(){return times;}
}
