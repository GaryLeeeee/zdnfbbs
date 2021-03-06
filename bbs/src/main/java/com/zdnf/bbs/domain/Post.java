package com.zdnf.bbs.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * Created by ZDNF on 2017/5/14.
 */
public class Post {
    public int id;
    @NotNull
    public String BelongTo;
    @Size(min=1,max=25,message = "标题长度为1-25个字符")
    public String title;
    @NotNull
    public String author;
    public int num=0;
    public String LastOne;
    public String LastTime;
    public int IsTop=0;

    public void setId(int id){this.id=id;}
    public int getId(){return id;}
    public void setBelongTo(String BT){this.BelongTo=BT;}
    public String getBelongTo(){return BelongTo;}
    public void setTitle(String title){this.title=title;}
    public String getTitle(){return title;}
    public void setAuthor(String author){this.author=author;}
    public String getAuthor(){return author;}
    public void setNum(int num){this.num=num;}
    public int getNum(){return num;}
    public void setLastOne(String lastOne){this.LastOne=lastOne;}
    public String getLastOne(){return this.LastOne;}
    public void setLastTime(String time){this.LastTime=time;}
    public String getLastTime(){return LastTime;}
    public void setIsTop(int IsTop){this.IsTop=IsTop;}
    public int getIsTop(){return IsTop;}
}
