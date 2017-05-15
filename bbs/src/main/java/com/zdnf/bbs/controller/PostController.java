package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Post;
import com.zdnf.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/12.
 * 此类用来管理帖子的相关操作
 *
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService PostService;
    //传板块名返回所有帖子
    @RequestMapping("all")
    public List<Post> add(Post post){
        return PostService.all(post.getBelongTo());
    }

    //添加帖子


    //删除帖子


    //返回帖子的回复
}
