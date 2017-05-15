package com.zdnf.bbs.service;

import com.zdnf.bbs.dao.PostDao;
import com.zdnf.bbs.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/14.
 */
public class PostService {
    @Autowired
    PostDao PostDao;

    public List<Post> all(String name){return PostDao.all(name);}
}
