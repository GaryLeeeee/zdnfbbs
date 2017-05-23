package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;

import com.zdnf.bbs.domain.Fate;
/**
 * Created by 90488 on 2017/5/22.
 */
public interface FateDao {
    public boolean add(@Param("Fate")Fate fate);

    public boolean is_add(@Param("fateid")String fateid,@Param("user")String user);

    public boolean set_state(@Param("state")boolean state,@Param("fateid")String fateid,@Param("user")String user);

    public int amount(@Param("fateid")String fateid);
}
