package com.zdnf.bbs.service;

import com.zdnf.bbs.dao.JoinDao;
import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.domain.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.zdnf.bbs.tools.Mail;

/**
 * Created by ZDNF on 2017/7/13.
 */
@Service
public class JoinService {
    @Autowired
    JoinDao JoinDao;
    Mail sender =new Mail();


    public List<Join> GetAll(){return JoinDao.GetAll();}

    public Join GetById(int id){return JoinDao.GetById(id);}

    public void AddAssociation(Join join){JoinDao.AddAssociation(join.getName(),join.getMail());}

    public boolean AddPeople(Joiner joiner){
        if(JoinDao.AddPeople(joiner)) {
            //得到协会会长的邮箱
            String mail = JoinDao.GetById(Integer.valueOf(joiner.getAssociationId())).getMail();
            //邮件的正文
            String 论坛id="论坛用户名："+joiner.getUserName();
            String 真实姓名="\n真实姓名:"+joiner.getRealName();
            String 手机号="\n手机号:"+joiner.getTelNum();
            String 微信号="\n微信号"+joiner.getWeChat();
            String 简介="\n简介："+joiner.getIntroduction();
            String content =
                    "有人报名啦！ta的基本内容如下：\n" +
                    论坛id+
                    真实姓名+
                    手机号+
                    微信号+
                    简介;
            sender.send(mail,content);
            return true;
        }
    return false;
    }

    public List<Joiner> GetAllPeople(){return JoinDao.GetAllPeople();}

    public String GetUserNameById(String id){return JoinDao.GetUserNameById(id);}

    public String IsJoined(String id,String name){return JoinDao.IsJoined(id,name);}
}
