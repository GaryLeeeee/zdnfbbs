package com.zdnf.bbs.tools;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * Created by ZDNF on 2017/7/15.
 */
@Service
public class FileUp {
    public final String accessKey="dcNxos-8d9hb222GRRyI5dWlsMI0hARssZA_Bspn";
    public final String secretKey = "_NT5SwHFOKdCMYdlEAHf1QsQABtW1VzACBEdEkaS";
    public final String bucket = "touxiang";


    public String upload(String id, String username, Boolean IsExist) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
       // String accessKey = "dcNxos-8d9hb222GRRyI5dWlsMI0hARssZA_Bspn";
       // String secretKey = "_NT5SwHFOKdCMYdlEAHf1QsQABtW1VzACBEdEkaS";
       // String bucket = "touxiang";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = GlobalConfig.FilePath + id + ".jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = username;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            if (IsExist) {
                //这里执行删除工作
                upToken = auth.uploadToken(bucket,key);
            }
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //System.out.println(putRet.key);
            //System.out.println(putRet.hash);
            return "1";
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                return "0";
            }
        }
        return "0";
    }

    public boolean getFileInfo(String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
   //     String accessKey = "dcNxos-8d9hb222GRRyI5dWlsMI0hARssZA_Bspn";
   //     String secretKey = "_NT5SwHFOKdCMYdlEAHf1QsQABtW1VzACBEdEkaS";
   //     String bucket = "touxiang";
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, key);
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.mimeType);
            System.out.println(fileInfo.putTime);
            return true;
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
            return false;
        }
    }

    public void delete(String fileName) {
    //构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone0());
    //...其他参数参考类注释
 //   String accessKey = "your access key";
 //   String secretKey = "your secret key";
 //   String bucket = "your bucket name";
    String key = fileName;
    Auth auth = Auth.create(accessKey, secretKey);
    BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
        bucketManager.delete(bucket, key);
        }catch(Exception e) {
        System.out.print(e.toString());
       }
    }

}
