package com.zdnf.bbs.service;

import com.zdnf.bbs.dao.PostDao;
import com.zdnf.bbs.domain.Post;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/14.
 */
@Service
public class PostService {
    @Autowired
    PostDao PostDao;

    public List<Post> all(String name){return PostDao.all(name);}

    public boolean add(Post post){return PostDao.add(post);}

    public boolean delete(int id){return PostDao.delete(id);}
}
