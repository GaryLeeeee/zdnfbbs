package com.zdnf.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zdnf.bbs.service.TestService;

/**
 * Created by ZDNF on 2017/5/7.
 * 此类用来是一个读取数据库内容的一个demo
 */
@Controller
public class TestController {
    @Autowired
    TestService k;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam("id")int id){
        String name =k.find_name_by_id(id);
        return name;
    }
}
