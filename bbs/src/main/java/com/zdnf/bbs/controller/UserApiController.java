package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Post;
import com.zdnf.bbs.domain.Replay;
import com.zdnf.bbs.service.LoginService;
import com.zdnf.bbs.tools.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zdnf.bbs.service.UserApiService;
import com.zdnf.bbs.domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.qiniu.util.Auth;

/**
 * Created by Skysibule on 2017/5/11.
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
    public User UserInfo(String name) {
        User user = UserApiService.GetUserInfo(name);
        user.setPasswd("null");
        return user;
    }

    //传用户名返回用户的id
    @RequestMapping("getid")
    public String getid(String name) {
        return UserApiService.GetIdByName(name);
    }

    //返回账户密码对不对
    //传账号密码，返回 true或者false
    @RequestMapping("istrue")
    public String istrue(User user, HttpServletResponse response) {
        if (LoginService.get_passwd(user.getName(), user.getPasswd())) {
            Cookie cookie = new Cookie("ZDNF_name", user.getName());
            cookie.setMaxAge(3600*24*36); //设置cookie的过期时间是10s
            response.addCookie(cookie);
            return "true";
        }
        return "false";
    }

    //封禁账号

    //返回最近回复的
    @RequestMapping("userreplay")
    public List<Replay> reply(@RequestParam(value = "name") String name, @RequestParam(value = "page") int page) {
        return UserApiService.GetUserReply(name, page);
    }

    //返回发过的所有帖子
    @RequestMapping("userpost")
    public List<Post> post(@RequestParam(value = "name") String name, @RequestParam(value = "page") int page) {
        return UserApiService.GetUserPost(name, page);
    }

    //上传头像
    @RequestMapping("up")
    @ResponseBody
    public String up(@RequestParam("file") MultipartFile file,
                     @CookieValue(value = "id") String id,
                     @CookieValue(value = "key") String key)
                     throws NoSuchAlgorithmException {
        if(!key.equals(ToMd5(UserApiService.GetPasswdById(id)))){
            return "这不是你的账号";
        }
        if (UserApiService.up(file, id)) return "上传成功";
        return "上传失败";
    }

    //返回头像
    @RequestMapping("img")
    public void img(HttpServletResponse httpServletResponse, @RequestParam("id") String id) throws IOException {
        FileInputStream fis = null;
        httpServletResponse.setContentType("image/jpg");
        OutputStream out = httpServletResponse.getOutputStream();
        File file = new File(GlobalConfig.FilePath + UserApiService.GetIdByName(id) + ".jpg");
        //这里判断一下存不存在，没有就返回默认头像
        if (!file.exists()) file = new File(GlobalConfig.FilePath+"root.jpg");
        fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        out.write(b);
        out.flush();
    }


    //返回md5加密
    @RequestMapping("md5")
    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }

    //请求后返回当前用户
    @RequestMapping("user")
    public String getuser(@CookieValue(value = "id") String id) {
        return LoginService.GetNameById(id);
    }

    //上传图片token生成
    //一个参数 文件名
    @RequestMapping("token")
    public String UpToken(String FileName) {
        Auth auth = Auth.create("", "");
        return auth.uploadToken("", FileName);

    }


}
