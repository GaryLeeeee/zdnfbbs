package com.zdnf.bbs.dao;

import com.zdnf.bbs.domain.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by ZDNF on 2017/5/11.
 */
public interface UserApiDao {
    public User GetUserInfo(@Param("name")String name);
    public List<User> GetUserReply(@Param("name")String name,@Param("low")int low);
    public List<User> GetUserPost(@Param("name")String name,@Param("low")int low);
    public String get_id(@Param("name")String name);
    public String GetPasswdById(@Param("id") String id);
}
