package com.zdnf.bbs.controller;

import com.zdnf.bbs.tools.GlobalConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Skysibule on 2017/5/16.
 * 这个控制器用来返回普通的视图层
 * 注意：登陆注册的视图在LoginController里
 */


@Controller
public class ViewController {

    /********
    以下为返回手机端的路由
     ********/

    //返回主页
    @RequestMapping("/")
    public String index(){
        return "手机端主页";
    }

    //返回手机端页面
    @RequestMapping("m")
    public String m_index(){
        return "手机端主页";
    }

    //手机端  登录页面
    @RequestMapping("m/login")
    public String m_login(){
        return "手机端登录";
    }

    //手机端  注册页面
    @RequestMapping("m/regist")
    public String m_regist(){
        return "手机端注册";
    }

    //返回手机端的版面页面
    @RequestMapping("mplate")
    public String 手机端版面(){
        return "手机端板块分区页";
    }

    //返回公告页面
    @RequestMapping("notice")
    public String MyPage(){return "notice";}

    //板块点进去后 显示帖子
    @RequestMapping("platepost")
    public String ShowPost(){return "手机端显示帖子";}

    //发帖页面
    @RequestMapping("plateReplyPage")
    public String addPost(){return "plateReplyPage";}

    //帖子回复
    @RequestMapping("postReplyPage")
    public String addReply(){return "postReplyPage";}

    //点进帖子里边 显示回复
    @RequestMapping("post")
    public String Post(){return "post";}

    //返回个人页面
    @RequestMapping("mypage")
    public String Person(){return "个人页面";}


    @RequestMapping("skyisbule")
    public String setToken(String old,String newone){
        if (old.equals(GlobalConfig.token)){
            GlobalConfig.token=newone;
            return "true";
        }
        return "false";
    }


    //返回总纲
    @RequestMapping("superclass")
    public String superclass(){
        return "总纲";
    }

    //返回总纲
    @RequestMapping("rule")
    public String rule(){
        return "站规";
    }

    //返回FAQ帮助页面
    @RequestMapping("faq")
    public String faq(){
        return "faq";
    }

    //返回帮助页面
    @RequestMapping("help")
    public String help(){return "帮助";}

    //返回新生页面
    @RequestMapping("freshman")
    public String freshman(){return "新生";}


}
