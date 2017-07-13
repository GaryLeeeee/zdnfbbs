package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Post;
import com.zdnf.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.zdnf.bbs.domain.Login;

/**
 * Created by ZDNF on 2017/5/12.
 * 这个控制器用来管理帖子的相关操作
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService PostService;
    Login login = new Login();


    //按板块id和page获取板块下相应的数据
    @RequestMapping("get")
    public List<Post> get(@RequestParam(value="id")int id,@RequestParam(value="page")int page){
        return PostService.get(id,page);
    }

    //添加帖子
    @RequestMapping("add")
    public String add(@Valid Post post, @CookieValue(value="id")String id,@CookieValue(value="key")String key) throws InterruptedException, NoSuchAlgorithmException {
        if(login.istrue(id,key))return "false";
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
