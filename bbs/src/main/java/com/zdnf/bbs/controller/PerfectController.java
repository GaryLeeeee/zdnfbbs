package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Perfect;
import com.zdnf.bbs.service.PerfectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/22.
 * 此类用于管理匹配成功的表的数据
 */
@RestController
@RequestMapping("/api/perfect")
public class PerfectController {
    @Autowired
    PerfectService perfectService;

    // 添加匹配成功信息
    @RequestMapping("add")
    public String add(Perfect perfect){
        if (perfectService.add(perfect)) return "true";
        return "false";
    }

    // 删除指定信息
    @RequestMapping("delete")
    public String delete(int id){
        if (perfectService.delete(id)) return "true";
        return "false";
    }

    // 获取所有数据
    @RequestMapping("get_all")
    public List<Perfect> get_all(int page){
        return perfectService.get_all(page);
    }

    // 获取总数据量
    @RequestMapping("amount")
    public int amount(){
        return perfectService.amount();
    }
}
