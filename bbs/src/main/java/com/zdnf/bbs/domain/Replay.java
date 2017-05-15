package com.zdnf.bbs.domain;

/**
 * Created by ZDNF on 2017/5/14.
 */
public class Replay {
    private int father;//所属id
    private String auth;//作者
    private String content;//正文
    private String time;//时间

    public void setFather(int father){this.father=father;}
    public int getFather(){return father;}
    public void setAuth(String auth){this.auth=auth;}
    public String getAuth(){return auth;}
    public void setContent(String content){this.content=content;}

}
