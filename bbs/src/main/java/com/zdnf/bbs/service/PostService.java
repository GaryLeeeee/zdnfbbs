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

    public boolean delete(int id){
        if (PostDao.delete(id)&&PostDao.del_replay(id))return true;
        return false;
    }

    public List<Post> get(int id,int page){
        int top = PostDao.topnum(id);
        int low = 0;
        int max;
        if (page==1){
            max=page*10-1-top;
        }else{
            low=(page-1)*10-top;
            max=page*10-1-top;
        }
        System.out.println(low+" "+max);
        return PostDao.get(id,low,max);
    }

    public boolean set_top(int id,int IsTop){return PostDao.set_top(id,IsTop);}

    public int TopNum(int id){return PostDao.topnum(id);}

    public List<Post> top(int id){return PostDao.top(id);}
}
