package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Disscusion;
import com.zenq.cloud507b.po.Tweet;
import com.zenq.cloud507b.po.vo.RetTweetDissqueue;
import com.zenq.cloud507b.service.DisscusionService;
import com.zenq.cloud507b.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 *
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@CrossOrigin(origins = "*")
@RestController
public class TweetController {
    @Autowired
    TweetService tweetService;

    @Autowired
    DisscusionService disscusionService;

    @GetMapping("/api/TweetList")
    public Object getListTweet(){
        try {
            return new SuccessMessage<List<RetTweetDissqueue>>("获取帖子信息成功",tweetService.tweetList()).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取帖子信息失败").getMessage();
        }
    }

    @PostMapping("/api/CreateTweet")
    public Object publishTweet(HttpServletRequest req){
        try {
            String id = req.getParameter("id");
            String title = "";
            String content = req.getParameter("content");
            String userid = req.getParameter("userid");
            String time = req.getParameter("time");
            String img = req.getParameter("img");

            //String follower = req.getParameter("follower");
            String type = req.getParameter("type");
            if(id == null || title == null || content == null  || userid == null || type== null ){
                return new ErrorMessage("参数缺失").getMessage();
            }else {
                return new SuccessMessage<Boolean>("发表帖子成功",tweetService.publishTweet(id, title, content, userid, time, img, type)).getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("发表帖子失败").getMessage();
        }
    }

    @PostMapping("/api/DelectTweet")
    public Object DelectTweet(HttpServletRequest req){
        try {
            String id = req.getParameter("id");
            return new SuccessMessage<Boolean>("删除帖子成功",tweetService.deleteTweet(id)).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("删除帖子失败").getMessage();
        }
    }
}
