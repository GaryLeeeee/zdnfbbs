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

    public boolean add(Replay replay){
        //对数据进行处理，防止注入
        replay.setContent(replay.getContent().replace("<script","<"));
        //更新数据
        if(ReplayDao.add(replay)&ReplayDao.repling(replay.getFather(),replay.getTimes()))
        return true;
        return false;
    }

    //每页返回10条
    public List<Replay> GetReplyByPostId(int id,int page){
        //首先算一下取第几条
        int low=(page-1)*10;
        return ReplayDao.GetReplyByPostId(id,low);
    }

    public boolean DeleteById(int id){return ReplayDao.DeleteById(id);}

    public int max(int id){return ReplayDao.max(id);}

    public String GetAuthorById(int id){return ReplayDao.GetAuthorById(id);}

    public List<Replay> SearchReply(String keyword,int page){
        return ReplayDao.SearchReply("%"+keyword+"%",(page-1)*10);
    }
}
