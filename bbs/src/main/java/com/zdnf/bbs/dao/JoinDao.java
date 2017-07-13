package com.zdnf.bbs.dao;

import java.util.List;
import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.domain.Joiner;

/**
 * Created by ZDNF on 2017/7/13.
 */
public interface JoinDao {
    public List<Join> GetAll();
    public Join GetById(int id);
    public void AddAssociation(String name,String mail);
    public boolean AddPeople(Joiner joiner);
}
