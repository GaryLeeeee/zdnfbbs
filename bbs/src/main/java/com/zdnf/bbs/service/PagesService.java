package com.zdnf.bbs.service;

import com.zdnf.bbs.dao.PagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZDNF on 2017/6/20.
 */
@Service
public class PagesService {
    @Autowired
    PagesDao PagesDao;

    public String GetPage(String url){return PagesDao.GetPage(url);}

    public boolean AddPage(String url,String content){return PagesDao.AddPage(url,content);}
}
