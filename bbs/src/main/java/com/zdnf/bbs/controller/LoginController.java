package com.zdnf.bbs.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zdnf.bbs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import com.zdnf.bbs.domain.User;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ZDNF on 2017/5/7.
 * 这个类用来处理用户的登录和注册
 */
@Controller
public class LoginController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    LoginService LoginService;

    //注册的表单
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String regist(){return "regist";}

    //注册表单提交
    @RequestMapping(value = "/registing",method = RequestMethod.POST)
    public String registing(HttpServletRequest httpServletRequest,User user){
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        if (captchaId.equals(parameter)) {
            LoginService.adduser(user);
            return "login";
        }
        return "error";
    }

    //登陆的表单
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    //处理登录
    @RequestMapping(value = "/logining",method = RequestMethod.POST)
    public String logining(HttpServletRequest httpServletRequest, HttpServletResponse response,User user){
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        //验证码和账号密码都正确
        if(captchaId.equals(parameter)&&LoginService.get_passwd(user.getName(),user.getPasswd())) {
            Cookie cookie = new Cookie("userid", user.getName()+"&"+user.getPasswd());
            cookie.setMaxAge(3600*24); //设置cookie的过期时间是一天
            response.addCookie(cookie);
            return "index";
        }
        return "error";
    }

    //用来生成验证码
    @RequestMapping("/Kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
