package com.zdnf.bbs.dao;

import java.util.List;
import com.zdnf.bbs.domain.Post;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/5/13.
 */
public interface PostDao {

    public List<Post> all(@Param("name")String name);
}
