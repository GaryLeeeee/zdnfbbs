package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Replay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zdnf.bbs.service.ReplayService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/15.
 * 这个控制器用来管理所有帖子的回复
 */
@RestController
@RequestMapping("/api/replay")
public class ReplayController {
    @Autowired
    ReplayService ReplayService;

    //查找回复
    @RequestMapping("select")
    public List<Replay> get_by_id(@RequestParam(value="id",required=true)int id,@RequestParam(value="page",required=true)int page){
        return ReplayService.get_by_id(id,page);
    }

    //添加回复
    @RequestMapping("add")
    public String add(Replay replay){
        if (ReplayService.add(replay)) return "true";
        return "false";
    }

    //删除回复
    @RequestMapping("delete")
    public String delete(@RequestParam(value = "id",required = true)int id){
        if (ReplayService.delete(id))return "true";
        return "false";
    }

    //返回此帖子都多少条回复
    @RequestMapping("max")
    public int max(@RequestParam(value = "id", required = true) int id){
        System.out.println(id);
        return ReplayService.max(id);
    }
}
