package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;
import com.zdnf.bbs.domain.Notice;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/14.
 */
public interface NoticeDao {
    public boolean add(@Param("Notice")Notice Notice);
    public List<Notice> GetAll();
    public Notice GetContentById(@Param("id")int id);
    public boolean DeleteById(@Param("id")int id);
}
