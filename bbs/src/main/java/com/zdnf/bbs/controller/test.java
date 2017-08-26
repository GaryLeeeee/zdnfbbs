package com.zdnf.bbs.controller;

import com.zdnf.bbs.tools.FileUp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZDNF on 2017/8/6.
 */
@RestController
public class test {
    FileUp a =new FileUp();

    @RequestMapping("test")
    public String tets(String name){
        a.delete("skyisbule");
        return "!";
    }

    @RequestMapping("gezi")
    public String gezi(String id){
        if(id.equals("1"))
        return "[{\"id\":1,\"title\":\"C基础\",\"content\":\"\\\\u5185\\\\u5bb9\"},{\"id\":2,\"title\":\"C基2础\",\"content\":\"测试\"},{\"id\":3,\"title\":\"C基3础\",\"content\":\"测试3\"}]";
    return "[{\"content\": \"hello\", \"id\": 1, \"title\": \"Hello\"}, {\"content\": \"\\u5185\\u5bb9\", \"id\": 2, \"title\": \"\\u6807\\u9898\"}]";
    }
}
