package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;
import com.zdnf.bbs.domain.Replay;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/15.
 */
public interface ReplayDao {
    public List<Replay> GetReplyByPostId(@Param("id")int id,@Param("low")int low);
    public boolean add(@Param("replay") Replay replay);
    public boolean DeleteById(@Param("id")int id);
    public int max(@Param("id")int id);
    public boolean repling(@Param("id")int id,@Param("LastTime")String LastTime);
}
