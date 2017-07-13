package com.zdnf.bbs.domain;

/**
 * Created by ZDNF on 2017/7/13.
 */
//协会名、用户名、真实姓名、入会简介、手机号、微信号。
public class Joiner {
    public String AssociationName;
    public String UserName;
    public String RealName;
    public String Introduction;
    public String TelNum;
    public String WeChat;

    public void setAssociationName(String str){AssociationName=str;}
    public String getAssociationName(){return AssociationName;}
    public void setUserName(String name){UserName=name;}
    public String getUserName(){return UserName;}
    public void setRealName(String name){RealName=name;}
    public String getRealName(){return RealName;}
    public void setIntroduction(String str){Introduction=str;}
    public String getIntroduction(){return Introduction;}
    public void setTelNum(String num){TelNum=num;}
    public String getTelNum(){return TelNum;}
    public void setWeChat(String str){WeChat=str;}
    public String getWeChat(){return WeChat;}
}
