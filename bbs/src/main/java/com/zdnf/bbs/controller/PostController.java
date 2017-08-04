package com.zdnf.bbs.controller;

import com.zdnf.bbs.dao.UserApiDao;
import com.zdnf.bbs.domain.Post;
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
import com.zdnf.bbs.tools.Login;

/**
 * Created by Skysibule on 2017/5/12.
 * 这个控制器用来管理帖子的相关操作
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService PostService;
    @Autowired
    UserApiDao UserApiDao;


    //按板块id和page获取板块下相应的数据
    @RequestMapping("get")
    public List<Post> GetPostsByIdAndPage(@RequestParam(value="id")int id,@RequestParam(value="page")int page){
        return PostService.GetPagesPostByBelongTo(id,page);
    }

    //添加帖子
    @RequestMapping("add")
    public String AddPost(@Valid Post post, @CookieValue(value="id")String id,@CookieValue(value="key")String key) throws InterruptedException, NoSuchAlgorithmException {
        if(!key.equals(ToMd5(UserApiDao.GetPasswdById(id)))){
            return "usererror";}
        if (PostService.add(post)){
            Thread.sleep(333);
            return PostService.add2(post);
        }
        return "false";
    }

    //删除帖子
    @RequestMapping("delete")
    public String DeletePostById(@RequestParam(value="id")int id){
        if (PostService.DeletePostById(id))return "true";
        return "false";
    }

    //将帖子置顶
    @RequestMapping("settop")
    public String SetTopById(@RequestParam(value="id")int id,@RequestParam(value = "istop")int istop){
        if (PostService.SetTop(id,istop))return "true";
        return "false";
    }

    //返回有多少条置顶贴
    @RequestMapping("topnum")
    public int GetTopNum(int id){
        return PostService.GetTopNumById(id);
    }

    //返回一个板块的置顶帖
    @RequestMapping("top")
    public List<Post> GetTopPosts(int id){
        return PostService.GetTopPostById(id);
    }

    //返回一个版块里有多少条帖子
    @RequestMapping("max")
    public int CountPostsNumById(int id){return PostService.CountPostsNumById(id);}

    //返回一个帖子的所有信息
    @RequestMapping("getallbyid")
    public List<Post> GetPostAllInfoById(int id){return PostService.GetOnePostAllInfoById(id);}


    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }
}
