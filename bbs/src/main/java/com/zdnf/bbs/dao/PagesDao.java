package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/6/20.
 */
public interface PagesDao {
    public String GetPage(String url);
    public Boolean AddPage(@Param("url") String url, @Param("content") String content);
}
