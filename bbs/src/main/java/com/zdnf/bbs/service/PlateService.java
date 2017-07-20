package com.zdnf.bbs.service;
import com.zdnf.bbs.domain.Plate;
import com.zdnf.bbs.dao.PlateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Skyisbule on 2017/5/12.
 */
@Service
public class PlateService {
    @Autowired
    PlateDao PlateDao;

    public List<Plate> GetAllPlateInfo(){return PlateDao.GetAllPlateInfo();}

    public String add(Plate Plate){
        if(PlateDao.add(Plate))
            return "添加成功";
        return "添加失败";
    }

    public String DeletePlateById(String id){
        try {
            PlateDao.DeletePlateById(id);
            return "删除成功";
        }catch (Exception e){
            return e.toString();
        }
    }

    public String UpdateNameById(int id,String name){
        try{
            PlateDao.UpdateNameById(id,name);
            return "修改成功";
        }catch (Exception e){
            return e.toString();
        }
    }

    public String GetNameById(int id){return PlateDao.GetNameById(id);}
}
