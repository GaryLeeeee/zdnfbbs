package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Notice;
import com.zdnf.bbs.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Skysibule on 2017/5/14.
 * 这个控制器是用来控制公告的
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    NoticeService NoticeService;

    //返回公告标题 已验证通过
    @RequestMapping("getall")
    public List<Notice> GetAll(){
        return NoticeService.GetAll();
    }

    //返回公告的内容 已验证通过
    @RequestMapping("content")
    public Notice GetContentByTitle(int id){
        return NoticeService.GetContentByTitle(id);
    }

    //添加公告 已验证通过
    //TODO！ 添加公告时要验证是否已存在
    //不然会返回错误
    @RequestMapping("add")
    public String add(@Valid Notice Notice){
        return NoticeService.add(Notice);
    }

    //删除公告 已验证通过
    @RequestMapping("delete")
    public String DeleteByTitle(int id){
        return NoticeService.DeleteByTitle(id);
    }

    //TODO! 这俩以后再写 懒
    //修改公告标题

    //修改公告内容
}
