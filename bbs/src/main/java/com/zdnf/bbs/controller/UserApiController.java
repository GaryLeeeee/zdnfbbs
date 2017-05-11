package com.zdnf.bbs.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("userinfo")
    public User UserInfo(@Param("name")String name){
        User user=UserApiService.get_user(name);
        user.setPasswd("*****");
        return user;
    }

}
