package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.domain.Login;
import com.zdnf.bbs.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.zdnf.bbs.domain.Joiner;

/**
 * Created by ZDNF on 2017/7/13.
 */
/**
要实现的功能：
 1.返回社团名字和id
 2.添加社团名字和邮箱
 3.发送邮件

 返回数据=》传id等信息=》查信息=》将信息插入表=》传相应信息给service 发邮件
 **/
@RestController
@RequestMapping("api/join")
public class JoinController {
    @Autowired
    JoinService JoinService;
    //登录验证
    @Autowired
    Login Login;

    //返回所有数据
    @RequestMapping("all")
    public List<Join> GetAll(){return JoinService.GetAll();}

    @RequestMapping("addassociation")
    public String 添加协会(Join join){
        JoinService.AddAssociation(join);
        return "true";
    }

    @RequestMapping("addpeople")
    public String 有人加入协会(@CookieValue(value="id",required=true)String id,@CookieValue("key")String key,Joiner Joiner) throws NoSuchAlgorithmException {
        if(Login.istrue(id,key)
                &&
                JoinService.AddPeople(Joiner))
            return "true";
        return "false";
    }

    @RequestMapping("allpeople")
    public List<Joiner> 返回所有人的报名信息(){
        return JoinService.GetAllPeople();
    }


}
