package com.zdnf.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zdnf.bbs.dao.ReplayDao;
import com.zdnf.bbs.domain.Replay;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/15.
 */
@Service
public class ReplayService {
    @Autowired
    ReplayDao ReplayDao;

    public boolean add(Replay replay){return ReplayDao.add(replay);}

    //每页返回10条
    public List<Replay> get_by_id(int id,int page){
        //首先算一下取第几条
        int low=(page-1)*10;
        int max=page*10-1;
        return ReplayDao.get_by_id(id,low,max);
    }

    public boolean delete(int id){return ReplayDao.delete(id);}

    public int max(int id){return ReplayDao.max(id);}
}
