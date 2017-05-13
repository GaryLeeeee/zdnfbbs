package com.zdnf.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zdnf.bbs.domain.Plate;
import com.zdnf.bbs.dao.PlateDao;
import com.zdnf.bbs.service.PlateService;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/12.
 * 此类用来管理板块的处理与返回
 */
@RestController
@RequestMapping("/api/plate")
public class PlateController {
    @Autowired
    PlateService PlateService;

    @RequestMapping("all")
    public List<Plate> all(){
        return PlateService.get_all();
    }

}
