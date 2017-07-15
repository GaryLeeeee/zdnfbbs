package com.zdnf.bbs.tools;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Mail{
    public void send(String mail,String content) {
        //step1： 构造HttpClient的实例,类似于打开浏览器
        HttpClient httpClient = new HttpClient();
        //step2： 创建GET方法的实例，类似于在浏览器地址栏输入url
        //"127.0.0.1?"+"mail="+mail+"&content="+content
        GetMethod getMethod = new GetMethod("127.0.0.1?"+"mail="+mail+"&content="+content);
        // 使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            //step3: 执行getMethod 类似于点击enter，让浏览器发出请求
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                //do nothing
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
    }
}