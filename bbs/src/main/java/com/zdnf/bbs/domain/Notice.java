package com.zdnf.bbs.domain;

import javax.validation.constraints.NotNull;

/**
 * Created by ZDNF on 2017/5/14.
 * 公告的实体类
 */
public class Notice {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String time;

    public void setTitle(String title){this.title=title;}
    public String getTitle(){return title;}
    public void setContent(String content){this.content=content;}
    public String getContent(){return content;}
    public void setTime(String time){this.time=time;}
    public String getTime(){return time;}
}
