package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;

import com.zdnf.bbs.domain.Confession;

import java.util.List;
/**
 * Created by 90488 on 2017/5/21.
 */
public interface ConfessionDao {
    public boolean add(@Param("Confession")Confession confession);

    public List<Confession> getconfe(@Param("min")String min,@Param("max")String max);

    public boolean delete(@Param("id")int id);
}
