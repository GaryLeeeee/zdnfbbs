package com.zdnf.bbs.domain;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

/**
 * Created by ZDNF on 2017/5/14.
 */
public class Replay {
    private int id;//自身的id
    @NotNull(message = "id不能为空")
    private int father;//所属id
    @NotNull(message = "作者不能为空")
    private String author;//作者
    @Length(min=1,max=5499,message = "datatoolong")
    private String content;//正文
    @NotNull
    private String times;//时间
    private int isfirst;//是不是一楼  0是flase 1是true
    private int isdeleted; //有没有被删除  同上

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
    public void setIsfirst(int Is){this.isfirst=Is;}
    public int getIsfirst(){return isfirst;}
    public void setIsdeleted(int Is){this.isdeleted=Is;}
    public int getIsdeleted(){return isdeleted;}
}
