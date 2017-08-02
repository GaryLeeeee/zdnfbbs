package com.zdnf.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Skysibule on 2017/5/16.
 * 这个控制器用来返回普通的视图层
 * 注意：登陆注册的视图在LoginController里
 */


@Controller
public class ViewController {

    //返回主页
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    //返回手机端页面
    @RequestMapping("m")
    public String m_index(){
        return "m_index";
    }

    //返回手机端的版面页面
    @RequestMapping("posts")
    public String 手机端版面(){
        return "posts";
    }

    //返回公告页面
    @RequestMapping("notice")
    public String MyPage(){return "notice";}

    //返回板块页面
    @RequestMapping("/t/{id}")
    public String Plate(){
        return "plate";
    }

    //板块点进去后 显示帖子
    @RequestMapping("platepost")
    public String ShowPost(){return "platepost";}

    //返回帖子查看页面
    @RequestMapping("post")
    public String Post(){return "post";}

    //返回个人页面
    @RequestMapping("mypage")
    public String Person(){return "mypage";}

    //返回个人页面，仅仅测试用
    @RequestMapping("user")
    public String UserPage(@CookieValue(value="ZDNF_name",required = false)String name){
        System.out.print(name);
        if (name!=null)
        return "redirect:/mypage?name="+name;
        return "redirect:/login";
    }
}
