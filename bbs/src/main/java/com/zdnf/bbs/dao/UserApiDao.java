package com.zdnf.bbs.dao;

import com.zdnf.bbs.domain.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by ZDNF on 2017/5/11.
 */
public interface UserApiDao {
    public User get_user_info(@Param("name")String name);

    public List<User> get_user_replay(@Param("name")String name,@Param("low")int low);

    public List<User> get_user_post(@Param("name")String name,@Param("low")int low);

    public String get_id(@Param("name")String name);
}
