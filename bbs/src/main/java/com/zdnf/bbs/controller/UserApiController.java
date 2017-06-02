package com.zdnf.bbs.controller;

import com.zdnf.bbs.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zdnf.bbs.service.UserApiService;
import com.zdnf.bbs.domain.User;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

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

    //传用户名返回用户的id
    @RequestMapping("getid")
    public String getid(@RequestParam("name")String name){
        return UserApiService.get_id(name);
    }

    //返回账户密码对不对
    //传账号密码，返回 true或者false
    @RequestMapping("istrue")
    public String istrue(User user){
        if(LoginService.get_passwd(user.getName(),user.getPasswd()))return "true";
        return "false";
    }

    //封禁账号

    //返回最近回复的
    @RequestMapping("userreplay")
    public User replay(@RequestParam(value="name")String name,@RequestParam(value="page")int page){
        return UserApiService.get_user_replay(name,page);
    }

    //返回发过的所有帖子
    @RequestMapping("userpost")
    public User post(@RequestParam(value="name")String name,@RequestParam(value="page")int page){
        return UserApiService.get_user_post(name,page);
    }

    //上传头像
    @RequestMapping("up")
    public String up(HttpServletRequest request, @RequestParam("file") MultipartFile file,@RequestParam("username")String userid){
        if (UserApiService.up(request, file,userid))return "true";
        return "false";
    }

    //返回头像
    @RequestMapping("img")
    public void img(HttpServletResponse httpServletResponse,@RequestParam("id")String id) throws IOException {
        FileInputStream fis = null;
        httpServletResponse.setContentType("image/jpg");
        OutputStream out = httpServletResponse.getOutputStream();
        File file = new File("d:/bbs/userimgs/"+id+".jpg");
        //这里判断一下存不存在，没有就返回默认头像
        if (!file.exists())file = new File("d:/bbs/userimgs/root.jpg");
        fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        out.write(b);
        out.flush();
    }


    //返回md5加密
    @RequestMapping("md5")
    public String md5(String id){
        try{
            MessageDigest md =MessageDigest.getInstance("md5");
            md.update(id.getBytes());
            return new BigInteger(1,md.digest()).toString();
        }catch (Exception e){
            return "0";
        }
    }

}
