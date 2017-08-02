package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Replay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zdnf.bbs.service.ReplayService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.zdnf.bbs.tools.Login;

/**
 * Created by Skysibule on 2017/5/15.
 * 这个控制器用来管理所有帖子的回复
 */
@RestController
@RequestMapping("/api/replay")
public class ReplayController {
    @Autowired
    ReplayService ReplayService;
    Login Loginer = new Login();

    //查找回复
    @RequestMapping("select")
    public List<Replay> get_by_id(@RequestParam(value="id",required=true)int id,@RequestParam(value="page",required=true)int page){
        return ReplayService.GetReplyByPostId(id,page);
    }

    //添加回复
    @RequestMapping("add")
    public String add(@Valid Replay replay, @CookieValue(value = "id")String id, @CookieValue(value = "key")String key) throws NoSuchAlgorithmException {
       // if (!Loginer.istrue(id,key)){return "<p>请先登录</p>";}
        if (ReplayService.add(replay)) return "true";
        return "false";
    }

    //删除回复
    @RequestMapping("delete")
    public String delete(@RequestParam(value = "id",required = true)int id){
        if (ReplayService.DeleteById(id))return "true";
        return "false";
    }

    //返回此帖子都多少条回复
    @RequestMapping("max")
    public int max(@RequestParam(value = "id", required = true) int id){
        return ReplayService.max(id);
    }
}
