package com.zdnf.bbs.dao;

import java.util.List;
import com.zdnf.bbs.domain.Post;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/5/13.
 */
public interface PostDao {

    public List<Post> all(@Param("name")String name);

    public boolean add(@Param("post")Post post);

    public boolean delete(@Param("id")int id);

    public  List<Post> get(@Param("id")int id,@Param("low")int low,@Param("max")int max);

    public boolean set_top(@Param("id")int id,@Param("IsTop")int IsTop);

    public boolean del_replay(@Param("id")int id);

    public int topnum(@Param("id")int id);

    public List<Post> top(@Param("id")int id);

}
