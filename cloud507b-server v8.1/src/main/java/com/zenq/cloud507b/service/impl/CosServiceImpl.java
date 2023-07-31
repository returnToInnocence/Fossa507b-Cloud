package com.zenq.cloud507b.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.zenq.cloud507b.service.CosService;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class CosServiceImpl implements CosService {

    public void upload(String imgUrl,String imgname){
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDqUs67rKXT8WdV6QTFl0zkFUgbFelcEaf", "qPikVzHI2WjgMltkLfiV1GEySfbGfneE");
        // 2 设置bucket的区域,
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        //String localFilePath = "src/main/resources/static/img/test1.png";
        //需要上传的文件路径
        String localFilePath = imgUrl;
        // 4 指定要上传的文件
        File localFile = new File(localFilePath);
        // 5 指定文件将要存放的存储桶
        String bucketName = "chenxi-1305165427";
        // 6 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "507b/"+ imgname;
        System.out.println("上传后的cos路径" + key);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        System.out.println("putObjectRequest:" + putObjectRequest);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println("上传成功"+putObjectResult);
    }

    public void download(String imgUrl){
        //这里imgname有问题，如何获取然后传进来
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDqUs67rKXT8WdV6QTFl0zkFUgbFelcEaf", "qPikVzHI2WjgMltkLfiV1GEySfbGfneE");
        // 2 设置bucket的区域,
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 4 指定存储桶
        String bucketName = "chenxi-1305165427";
        // 5 指定文件 COS 上的路径 (文件夹/imgname.png)
        String key = "507b/test1.png";
        // 4 设置要下载到的本地路径 /img/imgname.png
        File downFile = new File("src/main/resources/static/img/test1.png");
        // 5 设置要下载的文件所在的 对象桶的名称 和对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
        System.out.println("下载成功"+downObjectMeta);
    }

    public void delet(String imgUrl){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDqUs67rKXT8WdV6QTFl0zkFUgbFelcEaf", "qPikVzHI2WjgMltkLfiV1GEySfbGfneE");
        // 2 设置bucket的区域,
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 4 指定存储桶
        String bucketName = "chenxi-1305165427";
        // 5 指定文件 COS 上的路径 (文件夹/文件名.img)
        String key = "507b/test3.png";
        // 6 指定要删除的 bucket 和对象键
        cosClient.deleteObject(bucketName, key);
        System.out.println("删除成功");
    }
}
