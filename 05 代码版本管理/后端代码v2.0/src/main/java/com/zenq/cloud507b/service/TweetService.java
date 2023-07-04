package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.DisscusionMapper;
import com.zenq.cloud507b.mapper.TweetMapper;
import com.zenq.cloud507b.po.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@Service
public class TweetService{
    @Autowired
    TweetMapper tweetMapper;

    @Autowired
    CosService cosService;

    @Autowired
    DisscusionMapper disscusionMapper;

    public List<Tweet> tweetList(){
        return tweetMapper.selectTweetList();
    }

    public String tweetFollower(String id){
        return tweetMapper.selectTweetFolowerByid(id);
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean publishTweet(String id, String title, String content, String userid, String time, String img, String type) throws Exception {
        String imgUrl = "null";
        String newimgUrl = "null";
        if(img != null && !img.equals("null")){
            String uuid = UUID.randomUUID().toString();
            imgUrl = "src/main/resources/static/img/" + uuid + ".png";
            String imgname = uuid + ".png";
            System.out.println(imgname);
            System.out.println(imgUrl);
            byte[] bytes = Base64.getMimeDecoder().decode(img.substring(img.indexOf("base64") + 7));
            //byte[] bytes = Base64.getDecoder().decode(img.substring(img.indexOf("base64") + 7));
            File file = new File(imgUrl);
            //System.out.println(file.createNewFile());
            if (file.createNewFile()) {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
            }
            //imgUrl = img;
            //有图片，则需要上传图片到腾讯云
            cosService.upload(imgUrl, imgname);
            newimgUrl = "https://chenxi-1305165427.cos.ap-shanghai.myqcloud.com/507b/" + imgname;
            tweetMapper.insertTweet(new Tweet(id,title,content,userid,time,newimgUrl,type));
        }else{
            //没有图片则直接直接将数据写入数据库
            System.out.println("没有图片");
            tweetMapper.insertTweet(new Tweet(id,title,content,userid,time,img,type));
        }
        return true;
    }

    public boolean deleteTweet(String id){
        return tweetMapper.deleteTweet(id) > 0;
    }
    public boolean addfollowTweet(String id , String followers){
        boolean flag = tweetMapper.updateTweetFollowersById(id,followers) > 0;
        return flag;
    }

    public boolean createfollowTweet(String id , String followers){
        boolean flag = tweetMapper.insertTweetFollowersById(id,followers) > 0;
        return flag;
    }

}
