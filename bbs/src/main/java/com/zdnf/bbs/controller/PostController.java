package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Post;
import com.zdnf.bbs.service.LoginService;
import com.zdnf.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by ZDNF on 2017/5/12.
 * 这个控制器用来管理帖子的相关操作
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService PostService;
    @Autowired
    LoginService LogingService;
    //传一个字符串 转md5
    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }

    //传入一个用户名和他的key  判断是否正确
    public boolean istrue(String name,String key) throws NoSuchAlgorithmException {
        String md5=ToMd5(LogingService.passwd(name));
        if (md5.equals(key))return true;
        return false;
    }


    //传板块名返回所有帖子
    @RequestMapping("all")
    public List<Post> all(Post post){
        return PostService.all(post.getBelongTo());
    }

    //按板块id和page获取板块下相应的数据
    @RequestMapping("get")
    public List<Post> get(@RequestParam(value="id")int id,@RequestParam(value="page")int page){
        return PostService.get(id,page);
    }

    //添加帖子
    @RequestMapping("add")
    public String add(@Valid Post post, @CookieValue(value="ZDNF_name")String name,@CookieValue(value="key")String key) throws InterruptedException, NoSuchAlgorithmException {
        if (!istrue(name, key)){
            return "<p>请登录谢谢</p>";
        }
        if (PostService.add(post)){
            Thread.sleep(333);
            return PostService.add2(post);
        }
        return "false";
    }

    //删除帖子
    @RequestMapping("delete")
    public String delete(@RequestParam(value="id")int id){
        if (PostService.delete(id))return "true";
        return "false";
    }

    //将帖子置顶
    @RequestMapping("settop")
    public String SetTop(@RequestParam(value="id")int id,@RequestParam(value = "istop")int istop){
        if (PostService.set_top(id,istop))return "true";
        return "false";
    }

    //返回有多少条置顶贴
    @RequestMapping("topnum")
    public int TopNum(int id){
        return PostService.TopNum(id);
    }

    //返回一个板块的置顶帖
    @RequestMapping("top")
    public List<Post> Top(int id){
        return PostService.top(id);
    }

    //返回一个版块里有多少条帖子
    @RequestMapping("max")
    public int max(int id){return PostService.max(id);}

    //返回一个帖子的所有信息
    @RequestMapping("getallbyid")
    public List<Post> GetAllById(int id){return PostService.getallbyid(id);}
}
