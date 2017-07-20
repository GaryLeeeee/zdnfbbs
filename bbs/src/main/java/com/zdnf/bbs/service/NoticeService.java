package com.zdnf.bbs.service;

import com.zdnf.bbs.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zdnf.bbs.dao.NoticeDao;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/14.
 */
@Service
public class NoticeService {
    @Autowired
    NoticeDao NoticeDao;

    public String add(Notice Notice) {
        try {
            NoticeDao.add(Notice);
            return "添加成功";
        } catch (Exception e) {
            return e.toString();
        }
    }

    public List<String> GetAllTitle(){return NoticeDao.GetAllTitle();}

    public String GetContentByTitle(String title){
        return NoticeDao.GetContentByTitle(title);
    }

    public String DeleteByTitle(String title){
        try{
            NoticeDao.DeleteByTitle(title);
        }catch (Exception e){
            return e.toString();
        }finally {
            return "未知错误";
        }
    }
}
