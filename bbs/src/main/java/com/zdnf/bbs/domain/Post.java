package com.zdnf.bbs.domain;

/**
 * Created by ZDNF on 2017/5/14.
 */
public class Post {
    public int id;
    public String BelongTo;
    public String title;
    public String author;
    public int num;
    public String LastOne;
    public String LastTime;
    public int IsTop;

    public void setId(int id){this.id=id;}
    public int getId(){return id;}
    public void setBelongTo(String BT){this.BelongTo=BT;}
    public String getBelongTo(){return BelongTo;}
    public void setTitle(String title){this.title=title;}
    public String getTitle(){return title;}
}
