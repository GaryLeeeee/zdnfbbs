package com.zdnf.bbs.dao;

import java.util.List;
import com.zdnf.bbs.domain.Post;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/5/13.
 */
public interface PostDao {


    public boolean add(@Param("post")Post post);

    public boolean delete(@Param("id")int id);

    public  List<Post> get(@Param("id")int id,@Param("low")int low);

    public boolean set_top(@Param("id")int id,@Param("IsTop")int IsTop);

    public boolean del_replay(@Param("id")int id);

    public int topnum(@Param("id")int id);

    public List<Post> top(@Param("id")int id);

    public int max(@Param("id")int id);

    public String frist(@Param("title")String title,@Param("author")String author,@Param("LastTime")String LastTime);

    public List<Post> getallbyid(@Param("id")int id);
}
