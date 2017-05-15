package com.zdnf.bbs.controller;

import com.zdnf.bbs.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zdnf.bbs.service.UserApiService;
import com.zdnf.bbs.domain.User;

/**
 * Created by ZDNF on 2017/5/11.
 * 这个控制器用来返回用户的一系列信息
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    UserApiService UserApiService;
    @Autowired
    LoginService LoginService;

    //返回用户的所有信息
    //TODO！权限判断，如果没登录，仅返回部分信息
    //用户名、个人简介、最近回复等。
    @RequestMapping("userinfo")
    public User UserInfo(@Param("name")String name){
        User user=UserApiService.get_user(name);
        user.setPasswd("*****");
        return user;
    }

    //返回账户密码对不对
    //传账号密码，返回 true或者false
    @RequestMapping(value = "istrue",method = RequestMethod.POST)
    public String istrue(User user){
        if(LoginService.get_passwd(user.getName(),user.getPasswd()))return "true";
        return "false";
    }

    //封禁账号

    //返回最近回复的

    //返回发过的所有帖子
}
