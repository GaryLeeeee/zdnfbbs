package com.zdnf.bbs.controller;

import com.zdnf.bbs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zdnf.bbs.service.UserApiService;
import com.zdnf.bbs.domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
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
        User user = UserApiService.get_user(name);
        user.setPasswd("null");
        return user;
    }

    //传用户名返回用户的id
    @RequestMapping("getid")
    public String getid(String name) {
        return UserApiService.get_id(name);
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
    public List<User> reply(@RequestParam(value = "name") String name, @RequestParam(value = "page") int page) {
        return UserApiService.GetUserReply(name, page);
    }

    //返回发过的所有帖子
    @RequestMapping("userpost")
    public List<User> post(@RequestParam(value = "name") String name, @RequestParam(value = "page") int page) {
        return UserApiService.GetUserPost(name, page);
    }

    //上传头像
    @RequestMapping("up")
    public String up(@RequestParam("file") MultipartFile file, @CookieValue(value = "ZDNF_name") String username) {
        if (UserApiService.up(file, UserApiService.get_id(username))) return "true";
        return "false";
    }

    //返回头像
    @RequestMapping("img")
    public void img(HttpServletResponse httpServletResponse, @RequestParam("id") String id) throws IOException {
        FileInputStream fis = null;
        httpServletResponse.setContentType("image/jpg");
        OutputStream out = httpServletResponse.getOutputStream();
        File file = new File("d:/bbs/userimgs/" + UserApiService.get_id(id) + ".jpg");
        //这里判断一下存不存在，没有就返回默认头像
        if (!file.exists()) file = new File("d:/bbs/userimgs/root.jpg");
        fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        out.write(b);
        out.flush();
    }


    //返回md5加密
    @RequestMapping("md5")
    public String md5(String id) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(id.getBytes());
            return new BigInteger(1, md.digest()).toString();
        } catch (Exception e) {
            return "0";
        }
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
        Auth auth = Auth.create("dcNxos-8d9hb222GRRyI5dWlsMI0hARssZA_Bspn", "_NT5SwHFOKdCMYdlEAHf1QsQABtW1VzACBEdEkaS");
        return auth.uploadToken("zdnfbbs", FileName);

    }

    /*
    @RequestMapping("upload")
    public String upload() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "dcNxos-8d9hb222GRRyI5dWlsMI0hARssZA_Bspn";
        String secretKey = "_NT5SwHFOKdCMYdlEAHf1QsQABtW1VzACBEdEkaS";
        String bucket = "touxiang";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\bbs\\userimgs\\1.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return "1";
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                return "0";
            }
        }
        return "0";
    }

    */

}
