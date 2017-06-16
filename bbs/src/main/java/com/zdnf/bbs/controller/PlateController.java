package com.zdnf.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zdnf.bbs.domain.Plate;
import com.zdnf.bbs.service.PlateService;

import java.util.List;

/**
 * Created by ZDNF on 2017/5/12.
 * 此类用来管理板块的处理与返回
 */
@RestController
@RequestMapping("/api/plate")
public class PlateController {
    @Autowired
    PlateService PlateService;

    //TODO 删除一个版块后 记得清除本版块内所有内容

    //返回所有的板块信息
    @RequestMapping("all")
    public List<Plate> all(){
        return PlateService.get_all();
    }

    //添加板块
    //TODO 权限判断！！！！！
    //已测试通过！
    @RequestMapping(value = "add")
    public String add(Plate plate){
        if (PlateService.add(plate)) return "success";
        return "error";
    }

    //删除板块 理论无误 但并没有测试
    //传板块id
    //TODO 这里代码写得不好，后期要改！
    @RequestMapping(value = "delete")
    public String delete(Plate plate){
        if (PlateService.delete(plate))
        return "success";
        return "error";
    }

    //修改一个板块的名字
    @RequestMapping("updatename")
    public String update(@RequestParam(value="id")int id,@RequestParam(value="name")String name){
        if (PlateService.update(id,name))return "true";
        return "false";
    }

    //传一个id 返回板块的名字
    @RequestMapping("namebyid")
    public String GetNameById(int id){
        return PlateService.namebyid(id);
    }
}
