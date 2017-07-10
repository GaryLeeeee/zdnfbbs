package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Replay;
import com.zdnf.bbs.service.LoginService;
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
 * Created by ZDNF on 2017/5/15.
 * 这个控制器用来管理所有帖子的回复
 */
@RestController
@RequestMapping("/api/replay")
public class ReplayController {
    @Autowired
    ReplayService ReplayService;
    @Autowired
    LoginService LoginService;

    //传一个字符串 转md5
    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }

    //传入一个用户名和他的key  判断是否正确
    public boolean istrue(String name,String key) throws NoSuchAlgorithmException {
        String md5=ToMd5(LoginService.passwd(name));
        if (md5.equals(key))return true;
        return false;
    }


    //查找回复
    @RequestMapping("select")
    public List<Replay> get_by_id(@RequestParam(value="id",required=true)int id,@RequestParam(value="page",required=true)int page){
        return ReplayService.get_by_id(id,page);
    }

    //添加回复
    @RequestMapping("add")
    public String add(@Valid Replay replay, @CookieValue(value = "id")String id, @CookieValue(value = "key")String key) throws NoSuchAlgorithmException {
        if (!istrue(LoginService.GetNameById(id),key)){
            return "<p>请先登录</p>";
        }
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
        return ReplayService.max(id);
    }
}
