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
}
