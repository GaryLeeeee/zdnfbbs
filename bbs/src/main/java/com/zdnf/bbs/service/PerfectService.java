package com.zdnf.bbs.service;

import com.zdnf.bbs.dao.PerfectDao;
import com.zdnf.bbs.domain.Perfect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 90488 on 2017/5/22.
 */
@Service
public class PerfectService {
    @Autowired
    PerfectDao perfectDao;

    public boolean add(Perfect perfect){
        return perfectDao.add(perfect);
    }

    public boolean delete(int id){
        return perfectDao.delete(id);
    }

    public List<Perfect> get_all(int page){
        // 计算查询的条数范围
        int min = (page - 1) * 20;
        int max = page * 20;
        return perfectDao.get_all( min, max);
    }

    public int amount(){
        return perfectDao.amount();
    }
}
