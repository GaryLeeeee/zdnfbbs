package com.zdnf.bbs.tools;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.zdnf.bbs.dao.UserApiDao;
import org.springframework.stereotype.Service;

/**
 * Created by ZDNF on 2017/7/13.
 * 封装一个类，用来判断用户是否登录
 * 验证用户登录数据数据是否有误
 */
@Service
public class Login {
    @Autowired
    UserApiDao UserApiDao;
    //返回 salt加字符串转md5后的结果
    public String ToMd5(String str) throws NoSuchAlgorithmException {
        String res="skyisblue"+str;
        MessageDigest md =MessageDigest.getInstance("md5");
        md.update(res.getBytes());
        return new BigInteger(1,md.digest()).toString();
    }

    //传入一个id和他的key  判断是否正确
    public boolean istrue(String id,String key) throws NoSuchAlgorithmException {
        String md5=ToMd5(UserApiDao.GetPasswdById(id));
        if (md5.equals(key))return true;
        return false;
    }
}
