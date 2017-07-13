package com.zdnf.bbs.service;

import com.zdnf.bbs.dao.JoinDao;
import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.domain.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZDNF on 2017/7/13.
 */
@Service
public class JoinService {
    @Autowired
    JoinDao JoinDao;

    public List<Join> GetAll(){return JoinDao.GetAll();}
    public Join GetById(int id){return JoinDao.GetById(id);}
    public void AddAssociation(Join join){JoinDao.AddAssociation(join.getName(),join.getName());}
    public boolean AddPeople(Joiner joiner){
        if(JoinDao.AddPeople(joiner))
            return true;
    return false;
    }
}
