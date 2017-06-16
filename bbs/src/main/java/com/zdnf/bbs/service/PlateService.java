package com.zdnf.bbs.service;
import com.zdnf.bbs.domain.Plate;
import com.zdnf.bbs.dao.PlateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/12.
 */
@Service
public class PlateService {
    @Autowired
    PlateDao PlateDao;

    public List<Plate> get_all(){return PlateDao.get_all();}

    public boolean add(Plate Plate){return PlateDao.add(Plate);}

    public boolean delete(Plate Plate){return PlateDao.delete(Plate);}

    public boolean update(int id,String name){
        return PlateDao.set_name(id,name);
    }

    public String namebyid(int id){return PlateDao.namebyid(id);}
}
