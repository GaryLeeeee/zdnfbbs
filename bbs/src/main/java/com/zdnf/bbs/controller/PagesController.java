package com.zdnf.bbs.controller;


import com.zdnf.bbs.service.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by ZDNF on 2017/6/20.
 * 用来创建界面
 */
@Controller
public class PagesController {
    @Autowired
    PagesService PageService;

    //返回pages
    @RequestMapping("pages/{url}")
    @ResponseBody
    public String Pages(@PathVariable(value="url")String url){
        return PageService.GetPage(url);
    }
    //添加page
    @RequestMapping("addpages")
    public String AddPages(String url,String content){
        //System.out.println(url+content);
        if (PageService.AddPage(url,content))return "redirect:/pages/"+url;
        return "error";
    }
}
