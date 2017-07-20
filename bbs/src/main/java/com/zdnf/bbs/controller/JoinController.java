package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.tools.Login;
import com.zdnf.bbs.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.zdnf.bbs.domain.Joiner;

/**
 * Created by Skysibule on 2017/7/13.
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
        //判断是否已经报名过

        if(JoinService.IsJoined(Joiner.getAssociationId(),JoinService.GetUserNameById(id))!=null)
            return "你已经报过这个协会了";
        //验证登录 拿到用户在论坛的id
        Joiner.setUserName(JoinService.GetUserNameById(id));
        if(Login.istrue(id,key)
                &&
                //添加进数据库并发送邮件
                JoinService.AddPeople(Joiner))
            return "true";
        return "false";
    }

    @RequestMapping("allpeople")
    public List<Joiner> 返回所有人的报名信息(){
        return JoinService.GetAllPeople();
    }


}
