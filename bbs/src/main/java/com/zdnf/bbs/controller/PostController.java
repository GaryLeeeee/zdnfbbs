package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Post;
import com.zdnf.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //传板块名返回所有帖子
    @RequestMapping("all")
    public List<Post> all(Post post){
        return PostService.all(post.getBelongTo());
    }

    //添加帖子
    @RequestMapping("add")
    public String add(Post post){
        if (PostService.add(post))return "true";
        return "false";
    }

    //删除帖子
    //传id
    @RequestMapping("delete")
    public String delete(Post post){
        if (PostService.delete(post.getId()))return "true";
        return "false";
    }

}
