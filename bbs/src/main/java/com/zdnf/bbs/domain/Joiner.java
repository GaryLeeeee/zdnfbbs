package com.zdnf.bbs.domain;

/**
 * Created by ZDNF on 2017/7/13.
 */
//协会名、用户名、真实姓名、入会简介、手机号、微信号。
public class Joiner {
    public String AssociationId;
    public String UserName;
    public String RealName;
    public String Introduction;
    public String TelNum;
    public String WeChat;

    public void setAssociationId(String str){this.AssociationId=str;}
    public String getAssociationId(){return AssociationId;}
    public void setUserName(String name){this.UserName=name;}
    public String getUserName(){return UserName;}
    public void setRealName(String name){this.RealName=name;}
    public String getRealName(){return RealName;}
    public void setIntroduction(String str){this.Introduction=str;}
    public String getIntroduction(){return Introduction;}
    public void setTelNum(String num){this.TelNum=num;}
    public String getTelNum(){return TelNum;}
    public void setWeChat(String str){this.WeChat=str;}
    public String getWeChat(){return WeChat;}
}
