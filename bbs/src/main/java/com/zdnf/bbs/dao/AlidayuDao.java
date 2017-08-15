package com.zdnf.bbs.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/8/14.
 */
public interface AlidayuDao {
    public void addTel(@Param("telnum")String telnum,
                       @Param("code")int code,
                       @Param("num")String num);
    public String getTodayNum(String telnum);
    public String getIdByTel(String telnum);
    public void success(@Param("telnum") String telnum,
                        @Param("code")int code);
    public String getCode(String telnum);
}
