package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/5/7.
 * 这个类的测试需要表有  id name 字段 分别为int 和varchar
 */
public interface TestDao {
    public String Test_find_name_by_id(@Param("id")int id);
}
