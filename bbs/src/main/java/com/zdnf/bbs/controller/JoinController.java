package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    //返回所有数据
    @RequestMapping("all")
    public List<Join> GetAll(){return JoinService.GetAll();}

    @RequestMapping("addassociation")
    public String 添加协会(Join join){JoinService.AddAssociation(join);return "true";}

    @RequestMapping
    public String 有人加入协会(@CookieValue(value="userid",required=true)String id,Joiner Joiner){
        if(JoinService.AddPeople(Joiner))return "true";
        return "false";
    }
}
