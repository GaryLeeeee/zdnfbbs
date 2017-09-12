package com.zdnf.bbs.controller;

import com.zdnf.bbs.domain.Replay;
import com.zdnf.bbs.service.PostService;
import com.zdnf.bbs.tools.GlobalConfig;
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
import java.util.Calendar;
import java.util.List;

/**
 * Created by Skysibule on 2017/5/15.
 * 这个控制器用来管理所有帖子的回复
 */
@RestController
@RequestMapping("/api/replay")
public class ReplayController {
    @Autowired
    ReplayService ReplayService;
    @Autowired
    com.zdnf.bbs.dao.UserApiDao UserApiDao;
    @Autowired
    PostService PostService;


    //查找回复
    @RequestMapping("select")
    public List<Replay> getReplyById(
            @RequestParam(value="id",required=true)int id,
            @RequestParam(value="page",required=true)int page){
        List<Replay> res = ReplayService.GetReplyByPostId(id,page);
        for (int i =0;i<res.size();i++){
            if (res.get(i).getIsdeleted()==1){
                Replay temp = res.get(i);
                temp.setAuthor("看什么f12");
                temp.setContent("看什么f12");
                res.set(i,temp);
            }
        }
        return res;
        //return ReplayService.GetReplyByPostId(id,page);
    }

    //添加回复
    @RequestMapping("add")
    public String add(@Valid Replay replay,
                      @CookieValue(value = "id")String id,
                      @CookieValue(value = "key")String key,
                      @RequestParam(name="isfirst",required=false,defaultValue="0") char isfirst)
                      throws NoSuchAlgorithmException {
        if(!key.equals(ToMd5(UserApiDao.GetPasswdById(id)))){
            return "usererror";
        }
        Calendar c = Calendar.getInstance();
        int mon=c.get(Calendar.MONTH) + 1;
        String time=""+c.get(Calendar.YEAR)+"-"+
                mon+"-"+
                c.get(Calendar.DATE)+" "+
                c.get(Calendar.HOUR_OF_DAY)+":"+
                c.get(Calendar.MINUTE)+":"+
                c.get(Calendar.SECOND);
        replay.setTimes(time);
        try{
            ReplayService.add(replay);
            return "true";
        }catch (Exception e){
            if (isfirst=='1'){
                PostService.DeletePostById(replay.getFather());
            }
        }
        return "false";
    }

    //删除回复
    @RequestMapping("delete")
    public String delete(
            @RequestParam(value="replyid" ,required = true)int replyid,
            @CookieValue(value="id")String id,
            @CookieValue(value="key")String key,
            @RequestParam(value="token",defaultValue = "123")String token)
            throws NoSuchAlgorithmException {
        if (token.equals(GlobalConfig.token)){
            ReplayService.DeleteById(replyid);
            return "删除成功";
        }
        if(!key.equals(ToMd5(UserApiDao.GetPasswdById(id)))
                &&
                UserApiDao.GetNameById(id).equals(ReplayService.GetAuthorById(replyid))){
            return "用户验证失败";
        }

        if (ReplayService.DeleteById(replyid))return "true";
        return "false";
    }

    //返回此帖子都多少条回复
    //做分页用
    @RequestMapping("max")
    public int max(@RequestParam(value = "id", required = true) int id){
        return ReplayService.max(id);
    }

    //模糊搜索
    @RequestMapping("searchreply")
    public List<Replay> SearchReply(String keyword,int page){
        return ReplayService.SearchReply(keyword,page);
    }

    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }



}
