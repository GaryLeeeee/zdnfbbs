package com.zdnf.bbs.domain;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Mail{
    public void send() {
        //step1： 构造HttpClient的实例,类似于打开浏览器
        HttpClient httpClient = new HttpClient();
        //step2： 创建GET方法的实例，类似于在浏览器地址栏输入url
        GetMethod getMethod = new GetMethod("http://www.baidu.com");
        // 使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            //step3: 执行getMethod 类似于点击enter，让浏览器发出请求
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            //step4: 读取内容,浏览器返回结果
            //byte[] responseBody = getMethod.getResponseBody();
            //处理内容
           // System.out.println(new String(responseBody));
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            //System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接 （一定要记住）
            getMethod.releaseConnection();
        }
    }
}