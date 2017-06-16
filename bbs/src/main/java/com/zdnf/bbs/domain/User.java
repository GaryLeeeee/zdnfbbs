package com.zdnf.bbs.domain;

import javax.validation.constraints.Size;

/**
 * Created by ZDNF on 2017/5/7.
 */
public class User {
    @Size(min=1,max=8)
    private String name;//姓名
    @Size(min=1,max=20)
    private String passwd;//密码
    private String sex="神秘";//性别
    private String wechat="0";//微信
    private String telnum="0";//电话号
    private String introduce="无~";//个人简介
    private String power="c";//权限

    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public void setPasswd(String passwd){this.passwd=passwd;}
    public String getPasswd(){return passwd;}
    public void setSex(String sex){this.sex=sex;}
    public String getSex(){return sex;}
    public void setWechat(String wechat){this.wechat=wechat;}
    public String getWechat(){return wechat;}
    public void setTelnum(String telnum){this.telnum=telnum;}
    public String getTelnum(){return telnum;}
    public void setIntroduce(String introduce){this.introduce=introduce;}
    public String getIntroduce(){return introduce;}
    public void setPower(String power){this.power=power;}
    public String getPower(){return power;}
}
