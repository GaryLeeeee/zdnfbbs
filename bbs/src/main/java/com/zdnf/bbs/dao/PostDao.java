package com.zdnf.bbs.dao;

import java.util.List;
import com.zdnf.bbs.domain.Post;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/5/13.
 */
public interface PostDao {
    public boolean add(@Param("post")Post post);

    public boolean DeletePostById(@Param("id")int id);

    public  List<Post> GetPagesPostByBelongTo(@Param("id")int id,@Param("low")int low);

    public boolean SetTop(@Param("id")int id,@Param("IsTop")int IsTop);

    public boolean DeleteReplyById(@Param("id")int id);

    public int GetTopNumById(@Param("id")int id);

    public List<Post> GetTopPostById(@Param("id")int id);

    public int CountPostsNumById(@Param("id")int id);

    public String frist(@Param("title")String title,@Param("author")String author,@Param("LastTime")String LastTime);

    public List<Post> GetOnePostAllInfoById(@Param("id")int id);

    public List<Post> FindContent(@Param("KeyWord")String KeyWord,@Param("page")int page);

    public String GetLouZhu(int father);
}
