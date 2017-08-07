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

    public boolean DeletePostById(int id){
        if (PostDao.DeletePostById(id)&&PostDao.DeleteReplyById(id))return true;
        return false;
    }

    public List<Post> GetPagesPostByBelongTo(int id,int page){
        return PostDao.GetPagesPostByBelongTo(id,(page-1)*10);
    }

    public boolean SetTop(int id,int IsTop){return PostDao.SetTop(id,IsTop);}

    public int GetTopNumById(int id){return PostDao.GetTopNumById(id);}

    public List<Post> GetTopPostById(int id){return PostDao.GetTopPostById(id);}

    public int CountPostsNumById(int id){return PostDao.CountPostsNumById(id);}

    public String add2(Post post){return PostDao.frist(post.getTitle(),post.getAuthor(),post.getLastTime());}

    public List<Post> GetOnePostAllInfoById(int id){return PostDao.GetOnePostAllInfoById(id);}

    public List<Post> SearchPost(String KeyWord,int page){
        return PostDao.FindContent("%"+KeyWord+"%",(page-1)*10);
    }

    public String GetLouZhu(int PostId){
        return PostDao.GetLouZhu(PostId);
    }
}
