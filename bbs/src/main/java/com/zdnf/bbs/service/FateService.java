package com.zdnf.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdnf.bbs.dao.FateDao;
import com.zdnf.bbs.domain.Fate;
/**
 * Created by 90488 on 2017/5/22.
 */
@Service
public class FateService {
    @Autowired
    FateDao fateDao;

    public boolean add(Fate fate) {
        return fateDao.add(fate);
    }

    public String is_add(String fateid, String user) {
        try {
            return Boolean.toString(fateDao.is_add(fateid, user));
        } catch (Exception e) {
            return "NULL";
        }
    }

    public boolean update(boolean state, String fateid, String user) {
        return fateDao.set_state(state, fateid, user);
    }

    public int amount(String fateid) {
        return fateDao.amount(fateid);
    }
}

