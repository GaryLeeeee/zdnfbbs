package com.zdnf.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zdnf.bbs.dao.UserApiDao;
import com.zdnf.bbs.domain.User;
/**
 * Created by ZDNF on 2017/5/11.
 */
@Service
public class UserApiService {
    @Autowired
    UserApiDao UserApiDao;

    public User get_user(String name){return UserApiDao.get_user_info(name);}
}
