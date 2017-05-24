package com.zdnf.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdnf.bbs.domain.Confession;
import com.zdnf.bbs.dao.ConfessionDao;

import java.util.List;

/**
 * Created by 90488 on 2017/5/21.
 */
@Service
public class ConfessionService {
    @Autowired
    ConfessionDao confessionDao;

    // 添加表白信息
    public boolean add(Confession confession){
        return confessionDao.add(confession);
    }

    // 查询区域日期内的表白信息
    public List<Confession> getconfe(String min, String max){
        return confessionDao.getconfe(min, max);
    }

    // 查出指定表白信息
    public boolean delete(int id){
        return confessionDao.delete(id);
    }
}
