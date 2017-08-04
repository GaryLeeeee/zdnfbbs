package com.zdnf.bbs.controller;

import com.zdnf.bbs.dao.UserApiDao;
import com.zdnf.bbs.domain.Replay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zdnf.bbs.service.ReplayService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Skysibule on 2017/5/15.
 * 这个控制器用来管理所有帖子的回复
 */
@RestController
@RequestMapping("/api/replay")
public class ReplayController {
    @Autowired
    ReplayService ReplayService;
    @Autowired
    com.zdnf.bbs.dao.UserApiDao UserApiDao;


    //查找回复
    @RequestMapping("select")
    public List<Replay> getReplyById(@RequestParam(value="id",required=true)int id,@RequestParam(value="page",required=true)int page){
        return ReplayService.GetReplyByPostId(id,page);
    }

    //添加回复
    @RequestMapping("add")
    public String add(@Valid Replay replay, @CookieValue(value = "id")String id, @CookieValue(value = "key")String key) throws NoSuchAlgorithmException {
        if(!key.equals(ToMd5(UserApiDao.GetPasswdById(id)))){
            return "usererror";}
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
    //做分页用
    @RequestMapping("max")
    public int max(@RequestParam(value = "id", required = true) int id){
        return ReplayService.max(id);
    }

    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }

}
