package com.zdnf.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zdnf.bbs.domain.Fate;
import com.zdnf.bbs.service.FateService;
/**
 * Created by ZDNF on 2017/5/22.
 * 此类用来管理点赞表的
 * 1.添加点赞记录 2.修改点赞状态 3.统计点赞数量
 */
@RestController
@RequestMapping("/api/fate")
public class FateController {
    @Autowired
    FateService fateService;

    // 添加点赞记录
    @RequestMapping("add")
    public String add(Fate fate){
        if (fateService.add(fate)) return "true";
        return "false";
    }

    // 是否点赞
    @RequestMapping("isadd")
    public String isadd(String fateid,String user){
        try{
            return fateService.is_add(fateid,user);
        }catch (Exception e){
            return "NULL";
        }
        // return fateService.is_add(fateid,user);
    }

    // 修改点赞状态
    @RequestMapping("update")
    public String update(String fateid,String user){
        try{
            if (fateService.is_add(fateid,user) == "true")
                return Boolean.toString(fateService.update(false,fateid,user));
            else
                return Boolean.toString(fateService.update(true,fateid,user));
        }catch (Exception e){
            return "false";
        }

    }

    // 获取点赞总数
    @RequestMapping("amount")
    public int amount(String fateid){
        return fateService.amount(fateid);
    }
}
