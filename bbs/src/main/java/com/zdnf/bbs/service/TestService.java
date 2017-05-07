package com.zdnf.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.zdnf.bbs.dao.TestDao;
import org.springframework.stereotype.Service;

/**
 * Created by ZDNF on 2017/5/7.
 */
@Service
public class TestService {
    @Autowired
    TestDao TestDao;
    public String find_name_by_id(int id){
        String name = TestDao.Test_find_name_by_id(id);
        return "相应的用户名是"+name;
    }
}
