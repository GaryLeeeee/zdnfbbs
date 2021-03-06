package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;
import com.zdnf.bbs.domain.User;

/**
 * Created by ZDNF on 2017/5/7.
 */
public interface LoginDao {
    public void adduser(@Param("user")User user);
    public String GetUserPasswdByUserName(@Param("name")String name);
    public String HasUsername(@Param("name")String name);
    public String Passwd(String id);
    public String GetIdByName(String name);
    public String GetNameById(String id);
}
