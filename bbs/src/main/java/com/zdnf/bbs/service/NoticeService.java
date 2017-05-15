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

    public boolean add(Notice Notice){return NoticeDao.add(Notice);}

    public List<String> get_title(){return NoticeDao.get_title();}

    public String get_content(String title){
        return NoticeDao.get_content(title);
    }

    public boolean delete(String title){return NoticeDao.delete(title);}
}
