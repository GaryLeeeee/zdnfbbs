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

    //对post的内容进行处理
    public boolean add(Post post){
        return PostDao.add(post);
    }

    public boolean delete(int id){
        if (PostDao.delete(id)&&PostDao.del_replay(id))return true;
        return false;
    }

    public List<Post> get(int id,int page){
        return PostDao.get(id,(page-1)*10);
    }

    public boolean set_top(int id,int IsTop){return PostDao.set_top(id,IsTop);}

    public int TopNum(int id){return PostDao.topnum(id);}

    public List<Post> top(int id){return PostDao.top(id);}

    public int max(int id){return PostDao.max(id);}

    public String add2(Post post){return PostDao.frist(post.getTitle(),post.getAuthor(),post.getLastTime());}

    public List<Post> getallbyid(int id){return PostDao.getallbyid(id);}
}
