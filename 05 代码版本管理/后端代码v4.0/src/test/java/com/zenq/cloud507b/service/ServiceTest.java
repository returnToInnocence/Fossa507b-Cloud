package com.zenq.cloud507b.service;


import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.File;
import com.qcloud.cos.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送简单纯文本邮件
     */
    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("1019766824@qq.com", "发送邮件测试", "你爱我我爱你，蜜雪冰城甜蜜蜜");
    }
    @Test
    public void show() {
//        float rate = 11/14;
//        System.out.println(rate);
        System.out.println("test");
    }
    /**
     * 发送HTML邮件
     */
    @Test
    public void sendHtmlMail() {
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件" + "</font></h3></body></html>";
        mailService.sendHtmlMail("receiver@email.com", "发送邮件测试", content);
    }

    @Test
    public void pushImg(){
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDqUs67rKXT8WdV6QTFl0zkFUgbFelcEaf", "qPikVzHI2WjgMltkLfiV1GEySfbGfneE");
        // 2 设置bucket的区域,
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        String localFilePath = "src/main/resources/static/img/test1.png";
        // 4 指定要上传的文件
        File localFile = new File(localFilePath);
        // 5 指定文件将要存放的存储桶
        String bucketName = "chenxi-1305165427";
        // 6 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "507b/test1.png";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println("上传成功"+putObjectResult);
    }

    @Test
    public void getImg(){
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
        String key = "507b/test1.png";
        // 4 设置要下载到的本地路径
        File downFile = new File("src/main/resources/static/img/test1.png");
        // 5 设置要下载的文件所在的 对象桶的名称 和对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
        System.out.println("下载成功"+downObjectMeta);
    }

    @Test
    public void delImg(){
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
    /**
     * 发送带附件的邮件
     */
    @Test
    public void sendAttachmentMail() {
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有附件哦" + "</font></h3></body></html>";
        String filePath = "your file path";
        mailService.sendAttachmentMail("receiver@email.com", "发送邮件测试", content, filePath);
    }

    /**
     * 发送带图片的邮件
     */
    @Test
    public void sendInlineResourceMail() {
        String rscPath = "your picture path";
        String rscId = "skill001";
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有图片哦" + "</font></h3>"
                + "<img src=\'cid:" + rscId + "\'></body></html>";
        mailService.sendInlineResourceMail("receiver@email.com", "发送邮件测试", content, rscPath, rscId);
    }

    /**
     * 指定模板发送邮件
     */
    @Test
    public void testTemplateMail() {
        //向Thymeleaf模板传值，并解析成字符串
        Context context = new Context();
        context.setVariable("id", "001");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("receiver@email.com", "这是一个模板文件", emailContent);
    }
}

