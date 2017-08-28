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

    public List<Notice> GetAll(){return NoticeDao.GetAll();}

    public Notice GetContentByTitle(int id){
        return NoticeDao.GetContentById(id);
    }

    public String DeleteByTitle(int id){
        try{
            NoticeDao.DeleteById(id);
        }catch (Exception e){
            return e.toString();
        }finally {
            return "未知错误";
        }
    }
}
